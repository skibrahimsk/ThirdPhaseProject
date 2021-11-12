package com.example.datamodel.controller;

import com.example.datamodel.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class AdminLogin {

    @Autowired
    private AdminRepo adminRepo;

    @RequestMapping("/admin")
    public String admin() {
        return "adminLogin";
    }


    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam Map<String, String> maps) {
        String email = maps.get("email");
        String password = maps.get("password");
        if(adminRepo.verifyAdmin(email,password) ==null) {
            return "adminNotFound";
        }
        else  {
            return "adminHome";
        }
    }

}
