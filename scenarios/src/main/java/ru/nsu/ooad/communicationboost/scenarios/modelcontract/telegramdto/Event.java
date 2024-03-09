package ru.nsu.ooad.communicationboost.scenarios.modelcontract.telegramdto;

import java.io.Serializable;

public record Event(
    String username,
    String commandName,
    String messageBody) implements Serializable {
}