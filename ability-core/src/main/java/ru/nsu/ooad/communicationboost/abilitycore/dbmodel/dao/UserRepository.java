package ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    boolean existsUserByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findAllByRole(Role role);
}
