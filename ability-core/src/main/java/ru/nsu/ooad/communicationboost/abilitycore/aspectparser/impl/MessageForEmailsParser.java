package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.message.MessageForEmailsDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Aspect
@Component
public class MessageForEmailsParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.MessageForEmails)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String requiredParameters = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect MessageForEmailsParser
                Username: {};
                Required parameters <emails>, <message>: {};
                """,
                interModularDto.getUsername(),
                requiredParameters
        );

        String[] parts = parseMessageBySpace(requiredParameters);
        List<String> emailList = new ArrayList<>(Arrays.asList(parts).subList(0, parts.length - 1));

        MessageForEmailsDto messageDto = new MessageForEmailsDto(parts[parts.length - 1],
                                                                 emailList,
                                                                 interModularDto.getUsername());
        return proceedingJoinPoint.proceed(new Object[]{messageDto});
    }
}
