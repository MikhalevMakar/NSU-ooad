package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductByProductName(String productName);
}
