package ru.nsu.ooad.communicationboost.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot.api")
@PropertySource(value = "classpath:application.properties")
public class BotConfiguration {
    @Value(value = "${telegram.bot.api.username}")
    private String username;
    @Value(value = "${telegram.bot.api.token}")
    private String token;
}
