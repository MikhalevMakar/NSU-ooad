package ru.nsu.ooad.communicationboost.abilitycore.aspectparser;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public interface AspectParser {

   String DELIMITER = ", ";

    Object parseParametersForDto(ProceedingJoinPoint proceedingJoinPoint) throws Throwable;

    default String[] parseMessageBySpace(String requiredParameters) {
        return requiredParameters.split(DELIMITER);
    }
}
