package com.project.service;

import com.project.model.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id) throws Throwable;
    User updateUser(Long id, User updatedUser) throws Throwable;
    void deleteUser(Long id);
    User findUserByMobile(String mobileNumber);

}
