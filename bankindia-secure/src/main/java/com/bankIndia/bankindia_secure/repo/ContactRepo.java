package com.bankIndia.bankindia_secure.repo;

import com.bankIndia.bankindia_secure.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact , String> {
}
