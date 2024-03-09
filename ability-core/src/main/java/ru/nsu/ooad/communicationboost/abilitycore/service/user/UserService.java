package ru.nsu.ooad.communicationboost.abilitycore.service.user;

import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

public interface UserService {

    int INCREASE = 1;

    MessageResponse blockUser(DtoMarker usernameDto);

    MessageResponse unblockUser(DtoMarker usernameDto);

    MessageResponse changeRoleOfUser(DtoMarker userNewRoleDto);

    MessageResponse registrationUser(DtoMarker userDto);
}