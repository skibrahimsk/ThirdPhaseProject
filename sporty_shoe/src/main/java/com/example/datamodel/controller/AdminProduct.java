package com.example.datamodel.controller;

import com.example.datamodel.entity.Product;
import com.example.datamodel.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminProduct {

    @Autowired
    private ProductRepo productRepo;


    @RequestMapping("/getProducts")
    public String getProducts(Model model) {
        List<Product> products = productRepo.getAllProducts();
        model.addAttribute("products", products);
        return "adminHome";
    }

    @RequestMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product) {
        Product product1 = productRepo.addProduct(product);
        return "adminHome";
    }

    @RequestMapping("/delProduct")
    public String delProduct(@RequestParam("id") Long id) {
        productRepo.delProduct(id);
        return "adminHome";
    }


    @RequestMapping("/updateProduct")
    public String updateProduct(@RequestParam("id") Long id, @ModelAttribute Product product) {
        productRepo.updateProduct(id,product);
        return "adminHome";
    }

}
