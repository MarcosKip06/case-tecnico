package com.HubSpot.repository;

import com.HubSpot.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findTopByOrderByIdDesc();
}
