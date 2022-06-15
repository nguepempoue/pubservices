package com.example.pubservices.service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.pubservices.dto.MailBean;

import org.slf4j.Logger;

@Service
public class MailService {

     private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendMail(MailBean mail) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =  new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(new InternetAddress("nguepempouejulio@gmail.com"));
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getBody(),true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("erreur d'envoie du mail", e);
            throw new IllegalStateException("erreur d'envoie du mail");
        }
    }
}
