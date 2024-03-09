package ru.nsu.ooad.communicationboost.abilitycore.service.product.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.ProductRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Product;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductNameDto;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.exception.product.ProductNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.service.product.ProductService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Service(value = "DefaultProductService")
@RequiredArgsConstructor
@Primary
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public MessageResponse getProductInfo(DtoMarker productDto) {
        ProductNameDto productNameDto = (ProductNameDto) productDto;
        Product product = productRepository.findProductByProductName(productNameDto.getProductName())
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        return new MessageResponse(String.format("Product name: %s, description: %s", product.getProductName(),
                                                                                      product.getDescription()));
    }
}
