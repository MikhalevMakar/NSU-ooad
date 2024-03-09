package ru.nsu.ooad.communicationboost.abilitycore.dto.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommandInfoDto {
    @JsonProperty(value = "command_info")
    private String commandInfo;
    @JsonProperty(value = "required_role")
    private String requiredRole;
}
