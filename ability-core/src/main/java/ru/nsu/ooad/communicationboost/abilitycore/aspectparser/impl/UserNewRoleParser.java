package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserNewRoleDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class UserNewRoleParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.UserNewRole)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String role = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect UserNewRoleParser
                Username: {};
                Required parameter <role>: {};
                """,
                interModularDto.getUsername(), role
        );

        UserNewRoleDto userNewRoleDto = new UserNewRoleDto(interModularDto.getUsername(), role);
        return proceedingJoinPoint.proceed(new Object[]{userNewRoleDto});
    }
}
