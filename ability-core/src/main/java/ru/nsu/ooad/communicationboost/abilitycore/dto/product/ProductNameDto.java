package ru.nsu.ooad.communicationboost.abilitycore.dto.product;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Getter
@Setter
public class ProductNameDto extends DtoMarker {
    private String productName;

    public ProductNameDto(
            String username,
            String productName
    ) {
        super(username);
        this.productName = productName;
    }
}
