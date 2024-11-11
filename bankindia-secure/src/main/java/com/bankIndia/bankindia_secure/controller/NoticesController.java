package com.bankIndia.bankindia_secure.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices(){

        Authentication obj = SecurityContextHolder.getContext().getAuthentication();

        return "notices.." +obj.getName() ;
    }
}
