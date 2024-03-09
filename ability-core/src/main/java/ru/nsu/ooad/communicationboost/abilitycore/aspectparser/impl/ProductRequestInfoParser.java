package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.message.MessageForRolesDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductRequestInfoDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class ProductRequestInfoParser implements AspectParser {

    private static final int INDEX_PRODUCT = 0;

    private static final int INDEX_COUNT = 1;

    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.ProductRequestInfo)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String requiredParameters = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect ProductRequestInfoParser
                Username: {};
                Required parameters <product_name> <count>: {};
                """,
                interModularDto.getUsername(), requiredParameters
        );

        String[] parts = parseMessageBySpace(requiredParameters);

        ProductRequestInfoDto productDto = new ProductRequestInfoDto(interModularDto.getUsername(),
                                                                     parts[INDEX_PRODUCT],
                                                                     Integer.parseInt(parts[INDEX_COUNT]));
        return proceedingJoinPoint.proceed(new Object[]{productDto});
    }
}
