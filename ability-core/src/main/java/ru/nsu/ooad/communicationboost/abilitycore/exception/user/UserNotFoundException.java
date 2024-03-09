package ru.nsu.ooad.communicationboost.abilitycore.exception.user;

public class UserNotFoundException extends IllegalArgumentException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
