package com.example.pubservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pubservices.model.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long>{
    
}
