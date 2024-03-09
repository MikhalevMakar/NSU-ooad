package ru.nsu.ooad.communicationboost.emailapi.service.impl;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import ru.nsu.ooad.communicationboost.emailapi.model.EmailMessage;
import ru.nsu.ooad.communicationboost.emailapi.service.EmailService;

import lombok.RequiredArgsConstructor;

@Slf4j
@Primary
@RequiredArgsConstructor
public class EmailServiceImp implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendMessage(@NotNull EmailMessage emailMessage) {
        log.info("Send message from " + emailMessage.sender());
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        try {
            mailMessage.setFrom(emailMessage.sender());
            mailMessage.addRecipient(Message.RecipientType.TO,
                                     new InternetAddress(emailMessage.recipient()));
            mailMessage.setText(emailMessage.text());
            javaMailSender.send(mailMessage);
        } catch(MessagingException ex) {
            log.error("Failed to send message {}", emailMessage.recipient(), ex);
        }
    }
}