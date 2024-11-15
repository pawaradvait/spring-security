package com.bankIndia.bankindia_secure.controller;

import com.bankIndia.bankindia_secure.model.Customer;
import com.bankIndia.bankindia_secure.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private CustomerRepo customerRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//@PostMapping("/register")
//    public ResponseEntity<String> createUser(@RequestBody Customer customer) {
//
//    System.out.println(customer.getPwd());
//       customer.setPwd( passwordEncoder.encode(customer.getPwd()));
//
//        customerRepo.save(customer);
//
//        return ResponseEntity.ok("User created successfully");
//    }

    @GetMapping("/user")
    public ResponseEntity<Customer> createUser() {
        Authentication obj = SecurityContextHolder.getContext().getAuthentication();
       Customer customer = customerRepo.findByEmail(obj.getName()).orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return ResponseEntity.ok(customer);
    }

}
