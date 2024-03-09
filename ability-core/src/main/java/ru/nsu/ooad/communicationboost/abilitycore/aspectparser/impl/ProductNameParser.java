package ru.nsu.ooad.communicationboost.abilitycore.aspectparser.impl;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.aspectparser.AspectParser;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductNameDto;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

@Slf4j
@Aspect
@Component
public class ProductNameParser implements AspectParser {
    @Override
    @Around(value = "@annotation(ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.ProductName)")
    public Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        InterModularDto interModularDto = (InterModularDto) args[0];
        String productName = interModularDto.getRequiredParameters();

        log.info(
                """
                In aspect ProductNameParser
                Username: {};
                Required parameter <product_name>: {};
                """,
                interModularDto.getUsername(),
                productName
        );

        ProductNameDto productNameDto = new ProductNameDto(interModularDto.getUsername(),
                                                           productName);
        return proceedingJoinPoint.proceed(new Object[]{productNameDto});
    }
}
