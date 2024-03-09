package ru.nsu.ooad.communicationboost.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import ru.nsu.ooad.communicationboost.eventlistener.WrapperTelegramLongPollingBot;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties(BotConfiguration.class)
@ComponentScan(basePackages = "ru.nsu.ooad.communicationboost.configuration.button")
@Import(WrapperTelegramLongPollingBot.class)
@AutoConfiguration
public class AutoConfigurationBot {}