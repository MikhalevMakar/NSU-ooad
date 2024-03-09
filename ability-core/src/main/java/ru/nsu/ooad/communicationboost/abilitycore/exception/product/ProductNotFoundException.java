package ru.nsu.ooad.communicationboost.abilitycore.exception.product;

public class ProductNotFoundException extends IllegalArgumentException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
