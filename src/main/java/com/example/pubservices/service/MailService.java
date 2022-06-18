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

    public StringBuilder buildEmail(String title,String body,String object,String name, String link) {
        StringBuilder stringBuilder = new StringBuilder(); 
        stringBuilder.append("<!doctype html>");
        stringBuilder.append("<html lang=\"en-US\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />");
        stringBuilder.append("<title>olydis</title>");
        stringBuilder.append("<meta name=\"description\" content=\"Reset Password Email Template.\">");
        stringBuilder.append("<style type=\"text/css\">");
        stringBuilder.append("a {");
        stringBuilder.append("color: #26A9E1;");
        stringBuilder.append("cursor: pointer;");
        stringBuilder.append("outline: none !important;");
        stringBuilder.append("text-decoration: none !important;");
        stringBuilder.append("}");
        stringBuilder.append("a:hover {");
        stringBuilder.append("color: #2A3B90;");
        stringBuilder.append("text-decoration: underline !important;");
        stringBuilder.append("}");
        stringBuilder.append("svg{");
        stringBuilder.append("width:85px;");
        stringBuilder.append("}");
        stringBuilder.append("body {");
        stringBuilder.append("color: #000!important;");
        stringBuilder.append("font-size: 14px!important;");
        stringBuilder.append("background: #f9f9f9!important;");
        stringBuilder.append("font-family: 'Lato', sans-serif!important;");
        stringBuilder.append("}");
        stringBuilder.append("  </style>");
        stringBuilder.append("</head>");
        stringBuilder.append("<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px;\" leftmargin=\"0\">");
        stringBuilder.append("<!--100% body table-->");
        stringBuilder.append("<table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f9f9f9\"");
        stringBuilder.append("style=\"@import url(https://fonts.googleapis.com/css2?family=Lato:wght@300;400;700;900&display=swap); font-family:'Lato', sans-serif;\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>");
        stringBuilder.append("<table style=\" max-width:500px;  margin:0 auto;\" width=\"100%\" border=\"0\"");
        stringBuilder.append("align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:80px;\">&nbsp;</tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"text-align:left; \">");
        stringBuilder.append("<div style=\"margin-left:10px;\">");
        stringBuilder.append("</div>");
        stringBuilder.append("</td> </tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:20px;\">&nbsp;</td></tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td>");
        stringBuilder.append("<table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"");
        stringBuilder.append("style=\"max-width:500px;background:#fff; border-radius:3px;cursor: pointer; text-align:left;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:40px;\">&nbsp;</td> </tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"padding:0 35px; width: 90%;\">");
        stringBuilder.append("<h4 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:22px;\">");
        stringBuilder.append(""+title+"");
        stringBuilder.append("</h4>");
        stringBuilder.append("<br>");
        stringBuilder.append("<p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">");
        stringBuilder.append("Hi <label style=\"text-transform: capitalize;\">"+name+"</label> ,");
        stringBuilder.append(body);
        stringBuilder.append("</p>");
        stringBuilder.append("<a href=\""+link+"\"");
        stringBuilder.append("style=\"background:#2A3B90; text-align:center ; text-transform: uppercase; width: 88%; text-decoration:none !important; margin-top:35px; color:#fff; font-size:14px;padding:10px 24px;display:inline-block;border-radius:10px;\">"+object+"</a>");
        stringBuilder.append("</td> </tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:40px;\">&nbsp;</td>");
        stringBuilder.append("</tr></table></td>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:20px;\">&nbsp;</td>");
        stringBuilder.append("</tr><tr>");
        stringBuilder.append("<td style=\"text-align:center;\">");
        stringBuilder.append("<p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">");
        stringBuilder.append("Your have a question ? <a href=\"#\">Contact us</a></p>");
        stringBuilder.append("<span style=\"display:inline-block; vertical-align:middle; margin:10px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>");
        stringBuilder.append("</td></tr>");
        stringBuilder.append("<tr>");
        stringBuilder.append("<td style=\"height:80px;\">&nbsp;</td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("</td>");
        stringBuilder.append("</tr>");
        stringBuilder.append("</table>");
        stringBuilder.append("<!--/100% body table-->");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        return stringBuilder;

    }
}
