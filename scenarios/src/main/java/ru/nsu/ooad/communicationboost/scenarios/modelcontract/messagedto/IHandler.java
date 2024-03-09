package ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto;

import org.springframework.http.ResponseEntity;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

public interface IHandler {
    MetaInfo getMetaInformation();

    ResponseDescriptionArg getDescriptionArg();

    ResponseEntity<?> executeCommand(DtoMarker interModularDto);
}