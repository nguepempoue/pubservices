package com.example.pubservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.pubservices.model.Sector;
import com.example.pubservices.service.SectorService;

@RestController
@RequestMapping("sector")
// @CrossOrigin("*")
public class SectorController {
    
    @Autowired
    private SectorService sectorService;

    @PostMapping(path = "/add")
    public ResponseEntity<Sector> saveSector(@RequestBody Sector sector){
        return new ResponseEntity<>(this.sectorService.saveSector(sector), HttpStatus.CREATED);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Sector>> getAllSectors(){
        return new ResponseEntity<>(this.sectorService.getAllSector(), HttpStatus.OK);
    }

    @PutMapping(path="/update")
    public ResponseEntity<Sector> updateUser(@RequestParam Long id, @RequestBody Sector sector) {
        sector.setIdSector(id);
        return new ResponseEntity<>(this.sectorService.updateSector(sector), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long idSector){
        this.sectorService.deleteSector(idSector);
    }
}
