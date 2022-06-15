package com.example.pubservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pubservices.model.Sector;
import com.example.pubservices.repository.SectorRepository;

@Service
public class SectorService {
    
    @Autowired
    private SectorRepository sectorRepository;

    public Sector saveSector(Sector sector){
        return this.sectorRepository.save(sector);
    }

    public List<Sector> getAllSector(){
        return this.sectorRepository.findAll();
    }

    public Sector updateSector(Sector sector){
        return this.sectorRepository.save(sector);
    }

    public void deleteSector(Long idSector){
        this.sectorRepository.deleteById(idSector);
    }
}
