package com.SCM.SCM.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.SCM.SCM.helpers.AppConstants;
import com.SCM.SCM.helpers.ResouceNotFoundException;
import com.SCM.SCM.entities.User;
import com.SCM.SCM.repositories.UserRepo;
import com.SCM.SCM.services.UserService;


@Service
public class UserServiceImpl implements UserService {
     
    @Autowired
    private UserRepo userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserid(userId);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set user role
        
        user.setRoleList(List.of(AppConstants.ROLL_USER));
        logger.info(user.getProvider().toString());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> updatUser(User user) {
        User user2 =  userRepo.findById(user.getUserid()).orElseThrow(()-> new ResouceNotFoundException("User not found"));
        user2.setName(user.getName());
        user2.setEmail(user.getEmail());
        user2.setPassword(user.getPassword());
        user2.setAbout(user.getAbout());
        user2.setPhoneNumber(user.getPhoneNumber());
        user2.setProfilePic(user.getProfilePic());
        user2.setEnabled(user.isEnabled());
        user2.setEmailVerified(user.isEmailVerified());
        user2.setPhoneVerfified(user.isPhoneVerfified());
        user2.setProvider(user.getProvider());
        user2.setProviderUserId(user.getProviderUserId());

        //save user updates
        User save = userRepo.save(user2);
        return Optional.ofNullable(save);

    } 


    @Override
    public void deleteUser(String id) {
        User user2 =  userRepo.findById(id).orElseThrow(()-> new ResouceNotFoundException("User not found"));
        userRepo.delete(user2);
    }   

    @Override
    public boolean isUserExistByEmail(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        return user != null ? true : false;
    }

    @Override
    public boolean isUserExist(String userId) {
        User user2 =  userRepo.findById(userId).orElse(null);
        return user2 != null ? true : false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

}
