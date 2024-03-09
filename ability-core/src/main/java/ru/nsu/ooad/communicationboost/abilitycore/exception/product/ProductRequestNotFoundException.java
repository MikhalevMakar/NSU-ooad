package ru.nsu.ooad.communicationboost.abilitycore.exception.product;

public class ProductRequestNotFoundException extends IllegalArgumentException {
    public ProductRequestNotFoundException(String message) {
        super(message);
    }
}
