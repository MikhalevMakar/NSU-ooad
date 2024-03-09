package ru.nsu.ooad.communicationboost.abilitycore.exception.command;

public class GenerateCodeException extends IllegalStateException {

    public GenerateCodeException(String username) {
        super("Generate code exception for user: " + username);
    }
}
