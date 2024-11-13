package com.bankIndia.bankindia_secure.repo;

import com.bankIndia.bankindia_secure.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Cards, Long> {
    List<Cards> findByCustomerId(long id);
}
