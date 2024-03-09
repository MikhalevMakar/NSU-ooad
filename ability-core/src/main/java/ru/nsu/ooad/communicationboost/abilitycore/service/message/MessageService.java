package ru.nsu.ooad.communicationboost.abilitycore.service.message;

import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Service
public interface MessageService {
    MessageResponse sendMessageByEmail(DtoMarker messageForEmailsDto);

    MessageResponse sendMessageByRole(DtoMarker messageDto);
}
