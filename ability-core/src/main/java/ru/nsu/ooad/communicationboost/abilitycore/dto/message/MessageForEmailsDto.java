package ru.nsu.ooad.communicationboost.abilitycore.dto.message;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

import java.util.List;

@Getter
@Setter
public class MessageForEmailsDto extends DtoMarker {

    private String messageBody;

    private List<String> emailDtoList;

    public MessageForEmailsDto(
            String messageBody,
            List<String> emailDtoList,
            String username
    ) {
        super(username);
        this.messageBody = messageBody;
        this.emailDtoList = emailDtoList;
    }
}
