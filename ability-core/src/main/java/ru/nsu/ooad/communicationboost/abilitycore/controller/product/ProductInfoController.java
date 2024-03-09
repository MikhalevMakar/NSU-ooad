package ru.nsu.ooad.communicationboost.abilitycore.controller.product;

import lombok.RequiredArgsConstructor;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dto.annotations.ProductName;
import ru.nsu.ooad.communicationboost.abilitycore.service.product.ProductService;
import ru.nsu.ooad.communicationboost.abilitycore.util.UtilConsts;

import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.MetaInfo;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

@Component
@RequiredArgsConstructor
public class ProductInfoController implements IHandler {

    private final ProductService productService;

    @Value(value = "${commands.get.product.info.name}")
    private String commandName;

    @Value(value = "${commands.get.product.name.description.arg}")
    private String descriptionArg;

    @Override
    @ProductName
    public ResponseEntity<MessageResponse> executeCommand(DtoMarker interModularDto) {
        return ResponseEntity.ok(productService.getProductInfo(interModularDto));
    }

    @Override
    public MetaInfo getMetaInformation() {
        return new MetaInfo(UtilConsts.CommandConsts.GET_PRODUCT_INFO_URL, commandName);
    }

    @Override
    public ResponseDescriptionArg getDescriptionArg(){
        return new ResponseDescriptionArg(descriptionArg);
    }
}
