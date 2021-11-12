package com.example.datamodel.controller;

import com.example.datamodel.entity.User;
import com.example.datamodel.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@SessionAttributes({"user"})
public class UserLogin {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public String loginPage() {
        return "userLogin";
    }


    @PostMapping("/userLogin")
    public String loginUser(Model model, @RequestParam Map<String, String> maps, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = maps.get("email");
        String password = maps.get("password");
     User user = userRepo.verifyUser(email,password);
     request.getSession().setAttribute("user",user);

        if(userRepo.verifyUser(email,password) ==null) {
            return "userNotFound";
        }
        else {
            
            return "userHome";
        }
    }
}
