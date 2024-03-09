package ru.nsu.ooad.communicationboost.configuration.button;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public interface ButtonLine {
    SendMessage getButtonLine(long chatId);
    String getCategory();
}
