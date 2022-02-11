//package com.example.UserManagement.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.nio.charset.StandardCharsets;
//
//
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender sender;
//
//
//    @Autowired
//    public EmailService(JavaMailSender mailSender) {
//        this.sender = mailSender;
//    }
//
//    public MailResponse sendEmail(MailRequest request) {
//        MailResponse response = new MailResponse();
//        MimeMessage message = sender.createMimeMessage();
//        try {
//
//            // set mediaType
////            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
////                    StandardCharsets.UTF_8.name());
//
//
////            helper.setTo(request.getTo());
////            helper.setText("Test email", true);
////            helper.setSubject(request.getSubject());
////            helper.setFrom(request.getFrom());
////            sender.send(message);
//
//            response.setMessage("mail send to : " + request.getTo());
//            response.setStatus(Boolean.TRUE);
//
//        } catch (MessagingException e) {
//            response.setMessage("Mail Sending failure : "+e.getMessage());
//            response.setStatus(Boolean.FALSE);
//        }
//
//        return response;
//    }
//
//}