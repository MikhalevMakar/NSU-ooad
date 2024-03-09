package ru.nsu.ooad.communicationboost.abilitycore.security.service;

import ru.nsu.ooad.communicationboost.abilitycore.dto.security.ResponseSecrete;
import ru.nsu.ooad.communicationboost.abilitycore.dto.security.ResponseVerification;

public interface SecurityService {

    ResponseSecrete getCodeByUser(String username);

    ResponseVerification verificationCode(String username, int code);
}