package com.SCM.SCM.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.SCM.SCM.entities.User;
import com.SCM.SCM.forms.userForm;
import com.SCM.SCM.helpers.Message;
import com.SCM.SCM.helpers.MessageType;
import com.SCM.SCM.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


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
    public String processRegister(@Valid @ModelAttribute userForm userForm , BindingResult rBindingResult , HttpSession session){
        System.out.println("Processing register");
        System.out.println(userForm);
        // Validation form
        if(rBindingResult.hasErrors()){
            return "register";
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setAbout(userForm.getAbout());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://cdn-icons-png.flaticon.com/512/3135/3135715.png");
        User savedUser = userService.saveUser(user);
        System.out.println("UserSaved :"+savedUser);

        Message message =Message.builder().content("Registration successfull").type(MessageType.green).build();

        session.setAttribute("message" , message);
         
        return "redirect:/register";
    }


}
