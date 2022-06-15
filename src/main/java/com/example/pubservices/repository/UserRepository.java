package com.example.pubservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pubservices.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
    
}
