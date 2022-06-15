package com.example.pubservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pubservices.model.Role;
import com.example.pubservices.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    private RoleRepository repository;

    public void saveRole(Role role){
        this.repository.save(role);
    }

    public Role findByName(String name){
        Role role = this.repository.findByName(name);
        if(role == null){
            throw new IllegalStateException("Role does not exists !");
        }
        return this.repository.findByName(name);
    }

}
