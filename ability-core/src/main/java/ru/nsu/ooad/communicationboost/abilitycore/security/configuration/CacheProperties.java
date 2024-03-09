package ru.nsu.ooad.communicationboost.abilitycore.security.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;

@Setter
@Getter
@Slf4j
@Configuration
@ConfigurationProperties(prefix = "api.data.redis.duration-sec")
public class CacheProperties {

    private int duration;

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        log.info("redis cache configuration");
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(this.duration))
            .disableCachingNullValues();
    }
}