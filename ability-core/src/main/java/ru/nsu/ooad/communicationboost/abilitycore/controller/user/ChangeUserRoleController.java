package ru.nsu.ooad.communicationboost.abilitycore.controller.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.UserNewRole;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.service.user.UserService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

@Component
@RequiredArgsConstructor
public class ChangeUserRoleController implements IHandler {

    private final UserService userService;

    @Value(value = "${commands.change.user.role.name}")
    private String commandName;

    @Value(value = "${commands.get.change.role.description.arg}")
    private String descriptionArg;

    @Override
    @UserNewRole
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(userService.changeRoleOfUser(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.CHANGE_USER_ROLE_URL, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}
