package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserRegistrationDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class UserRegistrationParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.UserRegistration)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        int codeVerification = Integer.parseInt(interModularDto.getRequiredParameters().trim());
        log.info(
                """
                In aspect UserRegistrationParser
                Username: {};
                Required parameter <code_verification>: {};
                """,
                interModularDto.getUsername(), codeVerification
        );

        UserRegistrationDto userContextDto = new UserRegistrationDto(interModularDto.getUsername(),
                                                                     codeVerification);
        return proceedingJoinPoint.proceed(new Object[]{userContextDto});
    }
}
