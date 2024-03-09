package ru.nsu.ooad.communicationboost.abilitycore.exception.command;

public class CommandNotFoundException extends IllegalArgumentException {
    public CommandNotFoundException(String message) {
        super(message);
    }
}
