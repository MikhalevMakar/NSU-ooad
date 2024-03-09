package ru.nsu.ooad.communicationboost.scenarios.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.service.impl.IResolverServiceImpl;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@AutoConfiguration
public class AutoConfigurationScenarios {

    private final Set<IHandler> iHandlerList;

    @Bean
    public IResolverServiceImpl resolverServiceImpl() {
        log.info("Create resolver service bean");
        return new IResolverServiceImpl (iHandlerList);
    }
}