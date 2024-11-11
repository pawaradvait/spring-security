package com.bankIndia.bankindia_secure.controller;

import com.bankIndia.bankindia_secure.model.Customer;
import com.bankIndia.bankindia_secure.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

@PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody Customer customer) {

    System.out.println(customer.getPwd());
       customer.setPwd( passwordEncoder.encode(customer.getPwd()));

        customerRepo.save(customer);

        return ResponseEntity.ok("User created successfully");
    }

}
