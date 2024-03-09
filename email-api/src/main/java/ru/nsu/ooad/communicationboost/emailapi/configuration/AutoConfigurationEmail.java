package ru.nsu.ooad.communicationboost.emailapi.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import ru.nsu.ooad.communicationboost.emailapi.service.impl.EmailServiceImp;
import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(EmailConfiguration.class)
@AutoConfiguration
public class AutoConfigurationEmail {


    private final EmailConfiguration emailConfiguration;

    @Bean
    public JavaMailSender javaMailSender() {
        log.info("Create bean mail sender");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(emailConfiguration.getHost());
        mailSender.setPort(emailConfiguration.getPort());
        mailSender.setUsername(emailConfiguration.getUserName());
        mailSender.setPassword(emailConfiguration.getPassword());

        this.settingsProperties(mailSender);
        return mailSender;
    }

    @Bean
    public EmailServiceImp emailServiceImp(JavaMailSender javaMailSender) {
        log.info("Create email service bean");
        return new EmailServiceImp(javaMailSender);
    }

    private void settingsProperties(@NotNull JavaMailSenderImpl mailSender) {
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", emailConfiguration.getProperties().getAuth());

        props.setProperty("mail.transport.protocol", emailConfiguration.getProtocol());

        props.put("mail.smtp.starttls.enable", emailConfiguration.getProperties().getEnable());

        props.setProperty("mail.debug", emailConfiguration.getDebug());
    }
}