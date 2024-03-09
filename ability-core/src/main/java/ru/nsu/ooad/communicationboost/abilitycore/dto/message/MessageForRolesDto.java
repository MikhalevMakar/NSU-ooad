package ru.nsu.ooad.communicationboost.abilitycore.dto.message;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class MessageForRolesDto extends DtoMarker {
    private String messageBody;
    private String role;

    public MessageForRolesDto(
        String username,
        String role,
        String messageBody
    ) {
        super(username);
        this.messageBody = messageBody;
        this.role = role;
    }
}
