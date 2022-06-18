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
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("user")
@CrossOrigin("*")
public class UserController {

    String content = "<br>"+
    "<br>"+
    "Welcome to PubService !"+
    "<br>"+
    "<br>"+
    "Check your email address to make sure you receive important: ";

        
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
        User savedUser = this.userService.saveProvider(provider);
        String  link = ("http://localhost:4200/login/activate/" + savedUser.getIdUser());
        MailBean mail = new MailBean();
        mail.setTo(savedUser.getEmail());
        mail.setSubject("activate account");
        mail.setBody(mailService.buildEmail("Verify your email address",content,"Verify my email", savedUser.getFirstName(), link).toString());
        mailService.sendMail(mail);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
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

    @GetMapping(path = "/confirmToken")
    public ResponseEntity<User> confirmToken(@RequestParam("activated") String activated) {
      return new ResponseEntity<>(userService.confirmToken(activated), HttpStatus.OK);
    }

    @GetMapping(path = "/email")
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
      return new ResponseEntity<>(userService.findByEmail(email), HttpStatus.OK);
    }

    @GetMapping(path = "/id")
    public ResponseEntity<User> findById(@RequestParam("id") Long id) {
      return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    
}
