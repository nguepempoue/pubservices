package com.example.pubservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pubservices.repository.SectorRepository;

@Service
public class SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;
}
