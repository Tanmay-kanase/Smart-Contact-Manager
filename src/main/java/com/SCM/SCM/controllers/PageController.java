package com.SCM.SCM.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PageController {
        
    @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home page handler");
        model.addAttribute("name","Tanmay Kanase");
        model.addAttribute("Profesion", "Software Engineer");
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
}
