package ru.nsu.ooad.communicationboost.abilitycore.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class ProductRequestDeletionDto extends DtoMarker {

    private String productName;

    public ProductRequestDeletionDto(
            String username,
            String productName
    ) {
        super(username);
        this.productName = productName;
    }
}
