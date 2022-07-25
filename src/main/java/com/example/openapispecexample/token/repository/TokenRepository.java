package com.example.openapispecexample.token.repository;

import java.util.Optional;

import com.example.openapispecexample.token.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByUserId(long userId);

    void deleteByUserId(Long userId);

    boolean existsByUserId(Long userId);
}
