package com.example.UserManagement.utils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EmailUtility {
    private static final String FALSE="false";
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtility.class);
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");

    @Autowired
    Environment environment;

    public void sendmail(String toUser)
            throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put(environment.getProperty("mail.auth"), FALSE);
        props.put(environment.getProperty("mail.enable"), FALSE);
        props.put(environment.getProperty("mail.host"), environment.getProperty("mail.host.value"));
        props.put(environment.getProperty("mail.port"), environment.getProperty("mail.port.value"));

        Session session = Session.getDefaultInstance(props);
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(environment.getProperty("mail.address"), false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toUser));
//        msg.setSubject("[Mobile Discovery] : "+environment.getProperty("env")+"-"+appSource+" Web Scraper Status");
        msg.setSubject("User Application testing");
        String body = "Email from Usermanagement";

        msg.setContent(body, "text/html");
        msg.setSentDate(new Date());
        Transport.send(msg);
        Calendar cal = Calendar.getInstance();
        LOGGER.info("Email sent successfully "+dateFormat.format(cal.getTime()));
    }
}