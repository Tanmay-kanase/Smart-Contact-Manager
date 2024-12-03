package com.SCM.SCM.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SCM.SCM.helpers.Helper;
import com.SCM.SCM.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    // user dashboard
    @RequestMapping(value = "/dashboard" , method=RequestMethod.GET)
    public String userDashboard() {
        return "user/dashboard";
    }

    @RequestMapping(value = "/profile" , method=RequestMethod.GET)
    public String userProfile(Authentication authentication) {
        String username = Helper.getEmailofLoggedUser(authentication);
        logger.info("User Logged in : {}" ,username);

        
        // Fetch data from database
        return "user/profile";
    }
    
    
    // user add contact
    // user view contact
    // user edit contact
    // user delete contact
    // user search contact

}
