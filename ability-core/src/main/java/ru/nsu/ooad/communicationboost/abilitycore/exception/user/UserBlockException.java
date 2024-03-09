package ru.nsu.ooad.communicationboost.abilitycore.exception.user;


public class UserBlockException extends IllegalStateException {
    public UserBlockException(String username) {
        super("User is block" + username);
    }
}
