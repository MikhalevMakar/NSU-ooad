package ru.nsu.ooad.communicationboost.abilitycore.service.product;

import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Service
public interface ProductService {
    MessageResponse getProductInfo(DtoMarker productNameDto);
}
