package com.SCM.SCM.helpers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.SCM.SCM.forms.userForm;

public class Helper {
    public static String getEmailofLoggedUser(Authentication authentication){
        // if we logged in using email and password
        if(authentication instanceof OAuth2AuthenticationToken){
            var aOAuth2AuthenticationToken =  (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            String username = "";

            var oaruth2User = (OAuth2User) authentication.getPrincipal();
            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Getting email from google");
                username = oaruth2User.getAttribute("email").toString();
            }
            return username;


        }else{
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

        


        
    }

}
