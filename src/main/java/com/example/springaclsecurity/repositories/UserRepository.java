package com.example.springaclsecurity.repositories;

import com.example.springaclsecurity.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameIgnoreCase(String username);
}
