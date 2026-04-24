package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.services.UserService;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addUserToModel(Model model, Authentication authentication) {

        if (authentication != null
                && authentication.isAuthenticated()
                && !authentication.getName().equals("anonymousUser")) {

            String username = authentication.getName();

            User user = userService.getUserByEmail(username);

            model.addAttribute("user", user);
        }
    }
}