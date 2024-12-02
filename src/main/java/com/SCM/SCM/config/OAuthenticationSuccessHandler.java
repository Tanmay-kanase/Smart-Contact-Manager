package com.SCM.SCM.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.SCM.SCM.entities.Providers;
import com.SCM.SCM.entities.User;
import com.SCM.SCM.helpers.AppConstants;
import com.SCM.SCM.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthenticationSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthAuthenticationSuccessHandler");

        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();

        logger.info(user.getName());

        user.getAttributes().forEach((key,value)->{
            logger.info("{} : {}" , key , value);
        });

        logger.info(user.getAuthorities().toString());

        String email = user.getAttribute("email").toString();
        String name = user.getAttribute("name").toString();
        String picture = user.getAttribute("picture").toString();

        // create user and save it to database

        User user1 = new User();
        user1.setEmail(email);
        user1.setName(name);
        user1.setProfilePic(picture);
        user1.setPassword("password");
        user1.setUserid(UUID.randomUUID().toString());
        user1.setProvider(Providers.GOOGLE);
        user1.setEnabled(true);
        user1.setEmailVerified(true);
        user1.setProviderUserId(user.getName());
        user1.setRoleList(List.of(AppConstants.ROLL_USER));
        user1.setAbout("This account is created by google");
        
        User user2 = userRepo.findByEmail(email).orElse(null);
        System.out.println("User 2 ====================================="+user2);
        if(user2 == null){
            userRepo.save(user1);
            logger.info("User saved : "+email);
        }


        


        // Data saved in database 
        new DefaultRedirectStrategy().sendRedirect(request,response,"/user/profile");

    }
    // 
}
