package com.example.pubservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pubservices.dto.MailBean;
import com.example.pubservices.dto.ProviderBean;
import com.example.pubservices.model.User;
import com.example.pubservices.service.MailService;
import com.example.pubservices.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody ProviderBean provider){
        User useDB = this.userService.findByEmail(provider.getEmail());
        if(useDB != null){
            throw new IllegalStateException("User already exists !");
        }

        MailBean mail = new MailBean();
        mail.setTo(provider.getEmail());
        mail.setSubject("activate account");
        mail.setBody("content of test b2i mail");
        mailService.sendMail(mail);
        return new ResponseEntity<>(this.userService.saveProvider(provider), HttpStatus.CREATED);
    }

    @PutMapping(path="/update")
    public ResponseEntity<User> updateUser(@RequestParam Long id, @RequestBody User user) {
        user.setIdUser(id);
        return new ResponseEntity<>(this.userService.updateUser(user), HttpStatus.OK);
    }

    @GetMapping(path="/all")
    public ResponseEntity<List<User>> getAllUsers(){
         return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }


    
}
