package ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto;

import java.io.Serializable;

public record Message(String username, String text) implements Serializable  {}