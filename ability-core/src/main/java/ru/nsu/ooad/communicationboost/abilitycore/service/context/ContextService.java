package ru.nsu.ooad.communicationboost.abilitycore.service.context;

import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Context;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;


public interface ContextService {

    int INCREASE = 1;

    int MAX_ATTEMPTS_REG = 3;

    Context findOrCreateContext(String email, String userName);

    void deleteContext(String email);

    Context findContext(String username);

    void save(Context context);

    void blockContext(String email);

    MessageResponse createContext(DtoMarker userDto);
}
