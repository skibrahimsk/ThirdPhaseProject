package com.example.datamodel.controller;

import com.example.datamodel.entity.User;
import com.example.datamodel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSignup {

    @Autowired
    private UserRepo userRepo;


    @RequestMapping("/signup")
    public String signUp() {
        return "userSignUp";
    }


    @RequestMapping("/UserSignUp")
    public String createUser(@ModelAttribute User user) {
        User u = userRepo.addNewUser(user);
        return "userLogin";
    }
}
