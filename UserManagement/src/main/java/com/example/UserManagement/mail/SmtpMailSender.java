package com.example.UserManagement.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.UserManagement.model.MailModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSender implements MailSender{
    private static final Log Log = LogFactory.getLog(SmtpMailSender.class);

    private final JavaMailSender smtpMailSender;

    public SmtpMailSender(JavaMailSender smtpMailSender) {
        this.smtpMailSender = smtpMailSender;
    }

    @Override
    public void sendMail(MailModel mailModel) throws MessagingException {
        MimeMessage mimeMessage = smtpMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setSubject(mailModel.getSubject());
        helper.setTo(mailModel.getTo());
        helper.setText(mailModel.getBody(), true);
        smtpMailSender.send(mimeMessage);
    }
}
