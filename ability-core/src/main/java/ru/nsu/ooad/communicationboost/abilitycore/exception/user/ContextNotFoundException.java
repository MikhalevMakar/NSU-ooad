package ru.nsu.ooad.communicationboost.abilitycore.exception.user;

public class ContextNotFoundException extends IllegalStateException {
    public ContextNotFoundException(String email) {
        super("Context not found for user " + email);
    }
}
