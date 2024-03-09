package ru.nsu.ooad.communicationboost.abilitycore.exception.message;

public class EmailNotFoundException extends IllegalArgumentException {
    public EmailNotFoundException(String email) {
        super("Email: " + email + " not found in database");
    }
}
