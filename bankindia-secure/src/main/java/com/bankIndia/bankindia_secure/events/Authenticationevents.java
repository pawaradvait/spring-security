package com.bankIndia.bankindia_secure.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Authenticationevents {

    @EventListener
    public void oonSuccessAuthentication(AuthenticationSuccessEvent authenticationevents){

        log.info("Login successful for the user : {}", authenticationevents.getAuthentication().getName());

    }

    @EventListener
    public void onfailureofAuthentication(AbstractAuthenticationFailureEvent authenticationevents) {

        log.info("Login failed for the user : {}", authenticationevents.getAuthentication().getName());
    }
}
