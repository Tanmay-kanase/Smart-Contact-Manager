package com.SCM.SCM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class PageController {
        
    @RequestMapping("/home")
    public String home(){
        System.out.println("Home page handler");
     return "home";
    }

    // aboute route
    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About page");
        return "about";
    }

    //service
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Service page");
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        System.out.println("Service page");
        return "contact";
    }

    @RequestMapping("/register")
    public String registerPage() {
        System.out.println("Service page");
        return "register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("Service page");
        return "login";
    }


}
