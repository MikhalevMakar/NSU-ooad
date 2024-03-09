package ru.nsu.ooad.communicationboost.emailapi.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.*;

@Slf4j
@Setter
@Getter
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "api.mail")
public class EmailConfiguration {

    private String host;
    private int port;
    private String userName;
    private String password;
    private String protocol;
    private String debug;

    @NestedConfigurationProperty
    private SmtpProperties properties = new SmtpProperties();

    @Getter
    @Setter
    public static class SmtpProperties {
        private String auth = "true";
        private String enable = "true";
    }
}
