package br.com.jota.finance.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void enviarEmail(String subject, String to, String message) {
        var email = new SimpleMailMessage();
        email.setFrom("jotasFinancias@gmail.com");
        email.setSubject(subject);
        email.setTo(to);
        email.setText(message);
        mailSender.send(email);
    }

}
