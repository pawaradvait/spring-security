package com.bankIndia.bankindia_secure.controller;

import com.bankIndia.bankindia_secure.model.Accounts;
import com.bankIndia.bankindia_secure.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam long id) {
        Accounts accounts = accountRepo.findByCustomerId(id).get();
        return accounts;
    }

}
