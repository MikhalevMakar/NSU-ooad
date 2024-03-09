package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserContextDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class UserContextParser implements AspectParser {

    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.UserContext)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String email =  interModularDto.getRequiredParameters().trim();
        log.info(
            """
            In aspect UserContextParser
            Username: {};
            Required parameter <email>: {};
            """,
            interModularDto.getUsername(), email
        );

        UserContextDto userContextDto = new UserContextDto(interModularDto.getUsername(), email);
        return proceedingJoinPoint.proceed(new Object[]{userContextDto});
    }
}

