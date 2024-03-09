package ru.nsu.ooad.communicationboost.abilitycore.controller.product;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.ProductRequestInfo;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.service.product.ProductRequestService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

@Component
@RequiredArgsConstructor
public class SendProductRequestController implements IHandler {

    private final ProductRequestService productRequestService;

    @Value(value = "${commands.send.product.request.name}")
    private String commandName;

    @Value(value = "${commands.get.product.name-count.product.description.arg}")
    private String descriptionArg;

    @Override
    @ProductRequestInfo
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(productRequestService.sendProductRequest(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.SEND_PRODUCT_REQUEST_URL, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}