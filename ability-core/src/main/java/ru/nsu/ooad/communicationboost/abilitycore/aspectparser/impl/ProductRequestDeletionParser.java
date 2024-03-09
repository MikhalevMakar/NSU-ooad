package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductRequestDeletionDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class ProductRequestDeletionParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.ProductRequestDeletion)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String productName = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect ProductRequestDeletionParser
                Username: {};
                Required parameter <product_name>: {};
                """,
                interModularDto.getUsername(), productName
        );

        ProductRequestDeletionDto productRequestDeletionDto = new ProductRequestDeletionDto(interModularDto.getUsername(),
                                                                                            productName);
        return proceedingJoinPoint.proceed(new Object[]{productRequestDeletionDto});
    }
}
