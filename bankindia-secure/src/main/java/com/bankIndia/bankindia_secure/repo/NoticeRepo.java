package com.bankIndia.bankindia_secure.repo;

import com.bankIndia.bankindia_secure.model.Notices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepo extends JpaRepository<Notices, Long> {

    @Query(value = "from Notices n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
    List<Notices> findAllActiveNotices();
}
