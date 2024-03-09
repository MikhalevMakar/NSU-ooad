package ru.nsu.ooad.communicationboost.abilitycore.mapper;

import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.ProductRequest;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductRequestInfoDto;

import java.util.function.Function;

@Component
public class ProductRequestInfoDtoMapper implements Function<ProductRequest, ProductRequestInfoDto> {
    @Override
    public ProductRequestInfoDto apply(ProductRequest productRequest) {
        return new ProductRequestInfoDto(
                productRequest.getUser().getUsername(),
                productRequest.getProduct().getProductName(),
                productRequest.getCount()
        );
    }
}
