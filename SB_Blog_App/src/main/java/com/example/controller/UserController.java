package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.binding.LoginForm;
import com.example.binding.SignUpForm;
import com.example.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller 
public class UserController {

    @Autowired 
    private UserService userService;

    @Autowired
    private HttpSession session;

    // @GetMapping ("/index")
    // public String index(){
    //     return "index";
    // }

    @GetMapping ("/logout")
    public String logout(){
        return "index";
    }

    @GetMapping ("/login")
    public String login(Model model){
        model.addAttribute("user", new LoginForm());
        return "login";
    }

    @PostMapping ("/login")
    public String handleLogin(@ModelAttribute ("user") LoginForm form, Model model, 
    HttpSession session){
        
        String status = userService.login(form);
        if (status.contains("success")) {
            session.setAttribute("email", form.getEmail());
            return "redirect:/dashboard";
        } 
        model.addAttribute("errorMsg", status);
        return "login";
    }

    @GetMapping ("/signup")
    public String signUp(Model model){
        model.addAttribute("user", new SignUpForm());
        return "signup";
    }

    @PostMapping ("/signup")
    public String handleSignUp(@ModelAttribute ("user") SignUpForm form, Model model){
        boolean status = userService.signUp(form);
        if (status) {
            model.addAttribute("successMsg", "Your account is created successfully");
        }else{
            model.addAttribute("errorMsg", " Please Choose Unique Email");
        }
        return "signup";
    }


}
