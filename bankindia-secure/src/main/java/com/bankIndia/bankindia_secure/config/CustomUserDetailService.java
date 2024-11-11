package com.bankIndia.bankindia_secure.config;

import com.bankIndia.bankindia_secure.model.Customer;
import com.bankIndia.bankindia_secure.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
   private CustomerRepo customerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         Customer customer =  customerRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
         return User.builder().username(customer.getEmail()).password(customer.getPwd()).roles(customer.getRole()).build();

    }
}
