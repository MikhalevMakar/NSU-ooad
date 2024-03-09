package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.ProductRequest;

import java.util.Optional;

@Repository
public interface ProductRequestRepository extends JpaRepository<ProductRequest, Long> {
    Optional<ProductRequest> findProductRequestByUserUsernameAndProductProductName(String username, String productName);
}
