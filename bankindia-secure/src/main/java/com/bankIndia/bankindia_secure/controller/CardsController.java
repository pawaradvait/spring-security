package com.bankIndia.bankindia_secure.controller;

import com.bankIndia.bankindia_secure.model.Cards;
import com.bankIndia.bankindia_secure.model.Customer;
import com.bankIndia.bankindia_secure.repo.CardRepo;
import com.bankIndia.bankindia_secure.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CardsController {
@Autowired
private  CardRepo cardsRepository;

@Autowired
private CustomerRepo customerRepository;

    @GetMapping("/myCards")
    public List<Cards> getCardDetails(@RequestParam String email) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (optionalCustomer.isPresent()) {
            List<Cards> cards = cardsRepository.findByCustomerId(optionalCustomer.get().getId());
            if (cards != null) {
                return cards;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
