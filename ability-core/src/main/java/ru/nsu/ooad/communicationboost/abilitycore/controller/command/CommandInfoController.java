package ru.nsu.ooad.communicationboost.abilitycore.controller.command;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.HelpCommand;
import ru.nsu.ooad.communicationboost.abilitycore.service.command.CommandService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;

@Component
@RequiredArgsConstructor
public class CommandInfoController implements IHandler {

    private final CommandService commandService;

    @Value(value = "${commands.get.command.info.name}")
    private String commandName;

    @Value(value = "${commands.get.command.info.description.arg}")
    private String descriptionArg;

    @Override
    @HelpCommand
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(commandService.getCommandInfo(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.GET_COMMAND_INFO, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}
