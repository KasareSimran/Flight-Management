package com.project.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true, length = 10)
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String gender;
    @Column(nullable = false)
    private LocalDate dob;



}
