package ru.nsu.ooad.communicationboost.abilitycore.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

public class UsernameDto extends DtoMarker {
    public UsernameDto(
            String username
    ) {
        super(username);
    }
}
