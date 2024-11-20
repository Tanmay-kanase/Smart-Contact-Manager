package com.SCM.SCM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SCM.SCM.entities.User;
import com.SCM.SCM.forms.userForm;
import com.SCM.SCM.services.UserService;


@Controller
public class PageController {
    @Autowired
    private UserService userService;
        
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

    @GetMapping("/register")
    public String register(Model model) {
        System.out.println("Service page");
        userForm userForm = new userForm();
        userForm.setName("Tanmay");
        userForm.setAbout("This is about section");
        model.addAttribute("userForm" , userForm);
        

        return "register";
    }

    @RequestMapping("/login")
    public String loginPage() {
        System.out.println("Service page");
        return "login";
    }

    // Processng Register
    
    @RequestMapping(value = "/do-register" , method = RequestMethod.POST)
    public String processRegister(@ModelAttribute userForm userForm){
        System.out.println("Processing register");
        System.out.println(userForm);
        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .build();
        User savedUser = userService.saveUser(user);
        System.out.println("UserSaved :"+savedUser);
        return "redirect:/home";
    }


}
