package ru.nsu.ooad.communicationboost.abilitycore.properitestgmessage;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Slf4j
@Setter
@Getter
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "api.tg.message")
public class MessageTg {
    private String codeConfirmation;

    @Value("${api.tg.message.failed.confirmation-code}")
    private String failedConfirmationCode;

    @Value("${api.tg.message.successfully.confirmation-code}")
    private String successfullyConfirmationCode;
}

// TODO: перенести в тг модуль
