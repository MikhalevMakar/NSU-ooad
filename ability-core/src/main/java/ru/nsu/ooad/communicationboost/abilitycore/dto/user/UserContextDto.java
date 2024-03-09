package ru.nsu.ooad.communicationboost.abilitycore.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;


@Getter
@Setter
public class UserContextDto extends DtoMarker {

    private String email;

    public UserContextDto(String username, String email) {
        super (username);
        this.email = email;
    }
}
