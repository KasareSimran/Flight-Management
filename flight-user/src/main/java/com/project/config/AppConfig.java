package com.project.config;




import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class AppConfig {

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
          return config.getAuthenticationManager();
     }

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
          http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                  .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
                  .addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class)
                  .csrf(csrf -> csrf.disable())
                  .cors(cors->cors.configurationSource(corsConfigurationSource()))
                  .httpBasic(Customizer.withDefaults());


          return http.build();

     }

     private CorsConfigurationSource corsConfigurationSource() {
          return new CorsConfigurationSource() {
               @Override
               public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration cfg=new CorsConfiguration();
                    cfg.setAllowedOrigins(Arrays.asList(
                            "http://localhost:5173/"));
                    cfg.setAllowedMethods(Collections.singletonList("*"));
                    cfg.setAllowCredentials(true);
                    cfg.setAllowedHeaders(Collections.singletonList("*"));
                    cfg.setExposedHeaders(Arrays.asList(
                            "Authorization"));
                    cfg.setMaxAge(3600L);
                    return cfg;
               }
          };
     }

     @Bean
     PasswordEncoder passwordEncoder(){

          return new BCryptPasswordEncoder();
     }
}
