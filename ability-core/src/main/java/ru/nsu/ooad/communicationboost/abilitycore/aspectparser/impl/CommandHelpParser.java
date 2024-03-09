package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.command.CommandUrlDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class CommandHelpParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.HelpCommand)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        InterModularDto interModularDto = (InterModularDto) proceedingJoinPoint.getArgs()[0];

        log.info(
                """
                In aspect CommandUrlParser
                Username: {};
                Required parameter <command_name>: {};
                """,
                interModularDto.getUsername(),
                interModularDto.getRequiredParameters()
        );

        CommandUrlDto commandUrlDto = new CommandUrlDto(interModularDto.getUsername(),
                                                        interModularDto.getRequiredParameters());
        return proceedingJoinPoint.proceed(new Object[]{commandUrlDto});
    }
}