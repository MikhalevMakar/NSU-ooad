package ru.nsu.ooad.communicationboost.abilitycore.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import ru.nsu.ooad.communicationboost.abilitycore.dto.security.ResponseSecrete;
import ru.nsu.ooad.communicationboost.abilitycore.dto.security.ResponseVerification;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.UserNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel.entity.SecretCode;
import ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel.repository.RedisRepository;
import ru.nsu.ooad.communicationboost.abilitycore.security.configuration.SecurityProperties;
import ru.nsu.ooad.communicationboost.abilitycore.security.service.SecurityService;

import java.util.Random;

@Slf4j
@Primary
@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final SecurityProperties properties;

    private final RedisRepository redisRepository;

    private final Random random = new Random();

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public ResponseSecrete getCodeByUser(String username) {
        int code = redisRepository
                .save(new SecretCode(username, this.generateCode()))
                .code();
        log.info("Generate code {} for: {}", code, username);
        return new ResponseSecrete(username, code);
    }

    public ResponseVerification verificationCode(String username, int code) {
        boolean status = redisRepository.findById(username)
                .orElseThrow(() -> new UserNotFoundException(username))
                .code() == code;

        return new ResponseVerification(username, status);
    }

    private int generateCode() {
        return random
                .nextInt(properties.getDurationNumberMax() - properties.getDurationNumberMin()) + properties.getDurationNumberMin();
    }
}
