package com.example.UserManagement.mail;

import com.example.UserManagement.model.MailModel;

import javax.mail.MessagingException;

public interface MailSender {
    public void sendMail(MailModel mailModel) throws MessagingException;
}
