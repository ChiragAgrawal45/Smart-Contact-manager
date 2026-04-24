package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // dashboard
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // profile
    @RequestMapping("/profile")
    public String userProfile(Model model, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {

            String username = authentication.getName(); // email/username

            User user = userService.getUserByEmail(username);

            model.addAttribute("user", user);
        }

        return "user/profile";
    }
}