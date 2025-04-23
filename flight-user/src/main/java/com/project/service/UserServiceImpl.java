package com.project.service;


import com.project.exception.UserException;
import com.project.model.Booking;
import com.project.model.User;
import com.project.repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private RestTemplate restTemplate;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);



    @Override
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=userRepo.findAll();
         for (User user : users) {
            try {
                // Call booking service for each user
                List<Booking> bookings = restTemplate.getForObject(
                        "http://localhost:7445/api/bookings/user/" + user.getId(),
                        List.class
                );
                user.setBookings(bookings);
            } catch (Exception e) {
                // Log and continue if booking service fails
                logger.error("Failed to fetch bookings for user id: " + user.getId(), e);
                user.setBookings(new ArrayList<>());
            }
        }


        return users;
    }

    @Override
    public User getUserById(Long id) throws Throwable {
        Optional<User> userOptional = userRepo.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserException("invalid user id");
        }

        User user = userOptional.get();

        // Fetch bookings from Booking Service
        List<Booking> bookingsOfUser = restTemplate.getForObject(
                "http://localhost:7445/api/bookings/user/"+user.getId(),
                ArrayList.class
        );

        user.setBookings(bookingsOfUser);

        return user;
    }


    @Override
    public User updateUser(Long id, User updatedUser) throws Throwable {
        User existingUser = getUserById(id);
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setDob(updatedUser.getDob());
        existingUser.setGender(updatedUser.getGender());
        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
         userRepo.deleteById(id);

    }

    @Override
    public User findUserByMobile(String mobileNumber) {
        return userRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new RuntimeException("User not found"));

    }


    public UserDetails loadUserByUsername(String mobileNumber) throws UsernameNotFoundException {
        User user = userRepo.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getMobileNumber(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
