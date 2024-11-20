package com.SCM.SCM.services;

import java.util.List;
import java.util.Optional;

import com.SCM.SCM.entities.User;

public interface UserService {
    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updatUser(User user);
    void deleteUser(String id);
    boolean isUserExistByEmail(String email);
    boolean isUserExist(String is);
    List<User> getAllUsers();
};
