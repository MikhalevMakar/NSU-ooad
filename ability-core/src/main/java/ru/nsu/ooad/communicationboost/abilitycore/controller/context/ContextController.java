package ru.nsu.ooad.communicationboost.abilitycore.controller.context;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.UserContext;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.service.context.ContextService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;

@Component
@RequiredArgsConstructor
public class ContextController implements IHandler {

    private final ContextService contextService;

    @Value(value = "${commands.start.name}")
    private String commandName;

    @Value(value = "${commands.get.context.description.arg}")
    private String descriptionArg;

    @Override
    @UserContext
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(contextService.createContext(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.START_URL, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}
