package ru.nsu.ooad.communicationboost.abilitycore.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductInfoDto {
    @JsonProperty(value = "product_name")
    private String productName;
    @JsonProperty(value = "description")
    private String description;
}
