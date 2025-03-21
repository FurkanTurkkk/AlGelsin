package com.AlGelsin.notification_service.service;

import com.AlGelsin.notification_service.config.NotificationRabbitConfig;
import org.AlGelsin.SendMailDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @RabbitListener(queues = NotificationRabbitConfig.MAIL_QUEUE)
    public void sendMail(SendMailDto mailDto){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(mailDto.getTo());
        mail.setSubject(mailDto.getSubject());
        mail.setText(mailDto.getText());
        javaMailSender.send(mail);
    }
}
