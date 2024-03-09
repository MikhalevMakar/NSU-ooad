package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Context;

import java.util.Optional;

@Repository
public interface ContextRepository extends JpaRepository<Context, Long> {

    Optional<Context> findByEmail(String email);

    void deleteByUsername(String username);

    Optional<Context> findByUsername(String userName);
}
