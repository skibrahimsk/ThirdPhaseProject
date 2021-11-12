package com.example.datamodel.controller;

import com.example.datamodel.entity.Product;
import com.example.datamodel.entity.User;
import com.example.datamodel.entity.UserOrder;
import com.example.datamodel.repository.ProductRepo;
import com.example.datamodel.repository.UserOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class UserHome {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserOrderRepo userOrderRepo;

    @RequestMapping("/add")
    public String addProduct(Model model,  HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = productRepo.getAllProducts();
        model.addAttribute("products",products);
        return "userHome";
    }
   
    @RequestMapping("/addProducts")
    public String addProductToOrder(Model model, @RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        Long userId = user.getId();
        UserOrder userOrder = productRepo.addProductToUserOrder(id,userId);
        Long orderId = userOrder.getId();
        Long price = userOrderRepo.addTotalCostToOrder(orderId);
        model.addAttribute("order",userOrder);
        model.addAttribute("price",price);
        return "UserOrder";
    }
}
