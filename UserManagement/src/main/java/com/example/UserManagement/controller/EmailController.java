package com.example.UserManagement.controller;

import com.example.UserManagement.mail.MailSender;
import com.example.UserManagement.model.MailModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/users")
public class EmailController {
    private final MailSender mailSender;

    public EmailController(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @PostMapping("/mail")
    public String send(@RequestBody MailModel mailModel) throws MessagingException {
        try{
            mailSender.sendMail(mailModel);
            return "Successfully sent an Email";
        }
        catch(Exception e){
            return e.getMessage();
        }
    }
}
