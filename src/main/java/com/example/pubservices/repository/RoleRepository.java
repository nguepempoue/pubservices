package com.example.pubservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pubservices.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findByName(String name);
    
}
