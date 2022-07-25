package com.example.openapispecexample.user.repository;

import java.util.Optional;

import com.example.openapispecexample.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(String oauthId);

}
