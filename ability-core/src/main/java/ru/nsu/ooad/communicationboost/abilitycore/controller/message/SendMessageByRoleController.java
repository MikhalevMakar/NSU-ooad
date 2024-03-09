package ru.nsu.ooad.communicationboost.abilitycore.controller.message;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.MessageForRoles;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.service.message.MessageService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

@Component
@RequiredArgsConstructor
public class SendMessageByRoleController implements IHandler {

    private final MessageService messageService;

    @Value(value = "${commands.send.message.by.role.name}")
    private String commandName;

    @Value(value = "${commands.get.send.message.by.role.description.arg}")
    private String descriptionArg;

    @Override
    @MessageForRoles
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(messageService.sendMessageByRole(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.SEND_MESSAGE_BY_ROLE_URL, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}