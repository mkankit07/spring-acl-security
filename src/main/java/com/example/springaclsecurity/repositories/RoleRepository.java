package com.example.springaclsecurity.repositories;

import com.example.springaclsecurity.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
