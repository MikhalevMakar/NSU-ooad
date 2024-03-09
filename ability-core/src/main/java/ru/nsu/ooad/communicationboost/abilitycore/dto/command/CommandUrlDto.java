package ru.nsu.ooad.communicationboost.abilitycore.dto.command;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Accessors(chain = true)
@Getter
@Setter
public class CommandUrlDto extends DtoMarker {
    @JsonProperty(value = "command_url")
    private String commandUrl;

    @JsonCreator
    public CommandUrlDto(
            @JsonProperty(value = "username")
            String username,
            @JsonProperty(value = "command_url")
            String commandUrl
    ) {
        super(username);
        this.commandUrl = commandUrl;
    }
}
