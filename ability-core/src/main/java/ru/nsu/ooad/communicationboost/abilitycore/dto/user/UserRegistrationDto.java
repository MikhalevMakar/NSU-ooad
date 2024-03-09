package ru.nsu.ooad.communicationboost.abilitycore.dto.user;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class UserRegistrationDto extends DtoMarker {

    private final int code;

    public UserRegistrationDto(String username, int code) {
        super(username);
        this.code = code;
    }
}
