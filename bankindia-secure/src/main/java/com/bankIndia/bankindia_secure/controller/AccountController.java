package com.bankIndia.bankindia_secure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/getAccount")
    public String getAccount(){
        return "accounts..";
    }

}