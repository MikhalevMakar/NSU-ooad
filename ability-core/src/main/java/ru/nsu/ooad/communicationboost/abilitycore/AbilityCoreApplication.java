package ru.nsu.ooad.communicationboost.abilitycore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableJpaRepositories(basePackages = "ru.nsu.ooad.communicationboost.abilitycore.dbmodel")
@EnableRedisRepositories(basePackages = "ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel")
@SpringBootApplication
public class AbilityCoreApplication  {

    public static void main(String[] args)  {
        SpringApplication.run(AbilityCoreApplication.class, args);
    }
}
