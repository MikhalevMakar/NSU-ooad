package ru.nsu.ooad.communicationboost.emailapi.service;

import jakarta.validation.constraints.NotNull;
import ru.nsu.ooad.communicationboost.emailapi.model.EmailMessage;

public interface EmailService {
    void sendMessage(@NotNull EmailMessage emailMessageDto);
}