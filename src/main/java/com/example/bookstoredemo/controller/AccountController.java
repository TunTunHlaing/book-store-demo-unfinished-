package com.example.bookstoredemo.controller;

import com.example.bookstoredemo.dao.CustomerDao;
import com.example.bookstoredemo.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @Autowired
    private CustomerDao customerDao;

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

   /* @GetMapping({"/","/home"})
    public String home(){
        return "index";
    }*/

}
