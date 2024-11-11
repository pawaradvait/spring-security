package com.eazybytes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String displayLoginPage(@RequestParam(name = "error" , required = false) String error , Model model) {

        String errorMessge = null;
        if(null != error) {
            errorMessge = "Username or Password is incorrect !!";
        }

        model.addAttribute("errorMessge", errorMessge);


        return "login.html";
    }

}