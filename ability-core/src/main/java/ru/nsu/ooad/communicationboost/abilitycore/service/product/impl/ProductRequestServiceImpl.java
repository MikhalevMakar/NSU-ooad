package ru.nsu.ooad.communicationboost.abilitycore.service.product.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.ProductRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.ProductRequestRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.UserRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Product;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.ProductRequest;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductRequestDeletionDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.product.ProductRequestInfoDto;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.exception.product.ProductNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.exception.product.ProductRequestNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.UserNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.mapper.ProductRequestInfoDtoMapper;
import ru.nsu.ooad.communicationboost.abilitycore.service.product.ProductRequestService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service(value = "defaultProductRequestService")
@RequiredArgsConstructor
@Primary
public class ProductRequestServiceImpl implements ProductRequestService {

    private final ProductRequestRepository productRequestRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ProductRequestInfoDtoMapper productRequestInfoMapper;

    @Value("${api.product.status.response.successful-accept}")
    private String successfulAcceptProduct;

    @Value("${api.product.status.response.successful-cancel}")
    private String successfulCancelProduct;

    @Value("${api.product.status.response.successful-request}")
    private String successfulProductRequest;

    @Override
    @Transactional
    public MessageResponse acceptProductRequest(DtoMarker productRequestDto) {
        ProductRequestInfoDto productRequestInfoDto = (ProductRequestInfoDto) productRequestDto;
        ProductRequest productRequest = productRequestRepository.findProductRequestByUserUsernameAndProductProductName(
                productRequestInfoDto.getUsername(),
                productRequestInfoDto.getProductName()
        ).orElseThrow(() -> new ProductRequestNotFoundException("Product request not found"));
        productRequest.setAcceptStatus(true);
        productRequestRepository.save(productRequest);

        return new MessageResponse(successfulAcceptProduct);
    }

    @Override
    @Transactional
    public MessageResponse cancelProductRequest(DtoMarker productRequestDto) { // TODO: consider different roles
        ProductRequestDeletionDto productRequestDeletionDto = (ProductRequestDeletionDto) productRequestDto;
        ProductRequest productRequest = productRequestRepository.findProductRequestByUserUsernameAndProductProductName(
                productRequestDeletionDto.getUsername(),
                productRequestDeletionDto.getProductName()
        ).orElseThrow(() -> new ProductRequestNotFoundException("Product request not found"));
        productRequestRepository.deleteById(productRequest.getProductRequestId());
        return new MessageResponse(successfulCancelProduct);
    }

    @Override
    public MessageResponse viewProductRequests() { // TODO: consider different roles (maybe using UsernameDto)
        return new MessageResponse(productRequestRepository.findAll()
                .stream()
                .map(productRequestInfoMapper)
                .toList().toString()); // FIXME
    }

    /**
     * Note: if product request for productName already exists then this method will update
     * that product request if it was not accepted
     *
     * @param productRequestDto
     */
    @Override
    @Transactional
    public MessageResponse sendProductRequest(DtoMarker productRequestDto) {
        ProductRequestInfoDto productRequestInfoDto = (ProductRequestInfoDto) productRequestDto;
        String username = productRequestInfoDto.getUsername();
        String productName = productRequestInfoDto.getProductName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Product product = productRepository.findProductByProductName(productName)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));
        Optional<ProductRequest> optionalProductRequest = productRequestRepository.findProductRequestByUserUsernameAndProductProductName(
                username,
                productName
        );

        optionalProductRequest.ifPresentOrElse(productRequest -> {
            int updatedCount = productRequestInfoDto.getCount();
            if (!productRequest.isAcceptStatus()) {
                updatedCount += productRequest.getCount();
            }
            productRequest.setCount(updatedCount);
            productRequestRepository.save(productRequest);
        }, () -> {
            ProductRequest productRequest = ProductRequest.builder()
                    .user(user)
                    .product(product)
                    .count(productRequestInfoDto.getCount())
                    .acceptStatus(false)
                    .dateOfCreation(LocalDateTime.now())
                    .build();
            productRequestRepository.save(productRequest);
        });

        return new MessageResponse(successfulProductRequest);
    }
}
