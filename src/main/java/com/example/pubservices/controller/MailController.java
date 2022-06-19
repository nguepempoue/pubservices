package com.example.pubservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pubservices.dto.MailBean;
import com.example.pubservices.service.MailService;

@RestController
@RequestMapping("mail")
// @CrossOrigin("*")
public class MailController {

    @Autowired
    private MailService mailService;
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendMail(@RequestBody MailBean mail) {
       this.mailService.sendMail(mail);
    }
}
