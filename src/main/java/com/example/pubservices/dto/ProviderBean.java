package com.example.pubservices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProviderBean {
    private String firstName;
    private String lastName;
    private String password;
    private int phoneNumber;
    private String email;
    private Long idSector;
}
