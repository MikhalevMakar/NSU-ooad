package ru.nsu.ooad.communicationboost.abilitycore.dto.product;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class ProductRequestInfoDto extends DtoMarker {
    private int count;
    private String productName;

    public ProductRequestInfoDto(
            String username,
            String productName,
            int count
    ) {
        super(username);
        this.productName = productName;
        this.count = count;
    }
}