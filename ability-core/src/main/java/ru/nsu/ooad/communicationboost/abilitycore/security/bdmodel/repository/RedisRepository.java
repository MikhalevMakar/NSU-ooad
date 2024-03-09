package ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel.entity.SecretCode;

@Repository
public interface RedisRepository extends KeyValueRepository<SecretCode, String> {
}