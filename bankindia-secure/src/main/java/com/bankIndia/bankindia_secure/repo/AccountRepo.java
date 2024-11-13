package com.bankIndia.bankindia_secure.repo;

import com.bankIndia.bankindia_secure.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Accounts , Long> {

    Optional<Accounts> findByCustomerId(long id) ;


}
