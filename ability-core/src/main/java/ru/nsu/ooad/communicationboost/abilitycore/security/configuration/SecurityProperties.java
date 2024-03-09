package ru.nsu.ooad.communicationboost.abilitycore.security.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix = "api.security.generate-code")
public class SecurityProperties {

    private int strength;

    private int durationNumberMin;

    private int durationNumberMax;
}
