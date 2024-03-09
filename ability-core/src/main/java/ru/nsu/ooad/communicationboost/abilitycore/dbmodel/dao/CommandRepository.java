package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Command;

import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {
    Optional<Command> findCommandByCommandUrl(String commandUrl);
}
