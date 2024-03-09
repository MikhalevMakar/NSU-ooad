package ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterModularDto extends DtoMarker {
    private String requiredParameters;

    public InterModularDto(String username, String requiredParameters) {
        super(username);
        this.requiredParameters = requiredParameters;
    }
}