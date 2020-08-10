package com.crud.traveller.service;

import com.crud.traveller.entity.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SimpleEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger (SimpleMailMessage.class);
    @Autowired
    JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info ("Starting email preparation..");
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage ();
            mailMessage.setTo (mail.getMailTo ());
            mailMessage.setSubject (mail.getSubject ());
            mailMessage.setText (mail.getMessage ());
            javaMailSender.send (mailMessage);
            LOGGER.info ("Email has been sent.");
        } catch (MailException e){
            LOGGER.error ("Failed to process email sending", e.getMessage(), e);
        }

    }
    private SimpleMailMessage createMailMessage(final Mail mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        //if (mail.getToCc()!=null && !mail.getToCc().equals("")){
        //   mailMessage.setCc(mail.getToCc());
        //  LOGGER.info("CC has been included");
        // }
        return mailMessage;
    }
}