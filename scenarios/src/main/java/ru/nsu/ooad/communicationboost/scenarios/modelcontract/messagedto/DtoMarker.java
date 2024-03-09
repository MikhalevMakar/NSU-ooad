package ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public abstract class DtoMarker implements Serializable {
    @JsonProperty(value = "username")
    protected final String username;
}