package com.example.pubservices.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idUser;
    private String firstName;
    private String lastName;
    private String password;
    private int phoneNumber;
    private String email;
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "idSector")
    private Sector sector;
    
}
