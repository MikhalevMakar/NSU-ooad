package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.message.MessageForRolesDto;

@Slf4j
@Aspect
@Component
public class MessageForRolesParser implements AspectParser {
    private static final int INDEX_ROLE = 0;

    private static final int INDEX_MESSAGE = 1;

    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.MessageForRoles)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String requiredParameters = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect MessageForRolesParser
                Username: {};
                Required parameters <role>, <message>: {};
                """,
                interModularDto.getUsername(), requiredParameters
        );

        String[] parts = parseMessageBySpace(requiredParameters);

        MessageForRolesDto messageDto = new MessageForRolesDto(interModularDto.getUsername(),
                                                               parts[INDEX_ROLE],
                                                               parts[INDEX_MESSAGE]);
        return proceedingJoinPoint.proceed(new Object[]{messageDto});
    }
}
