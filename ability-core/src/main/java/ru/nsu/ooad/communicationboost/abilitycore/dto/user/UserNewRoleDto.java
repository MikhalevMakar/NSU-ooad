package ru.nsu.ooad.communicationboost.abilitycore.dto.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class UserNewRoleDto extends DtoMarker {
    @JsonProperty(value = "role")
    private String role;

    @JsonCreator
    public UserNewRoleDto(
            @JsonProperty(value = "username")
            String username,
            @JsonProperty(value = "role")
            String role
    ) {
        super(username);
        this.role = role;
    }
}
