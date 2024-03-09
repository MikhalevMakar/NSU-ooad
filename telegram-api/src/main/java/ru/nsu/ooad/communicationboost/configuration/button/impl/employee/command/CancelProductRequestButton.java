package ru.nsu.ooad.communicationboost.configuration.button.impl.employee.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.nsu.ooad.communicationboost.configuration.button.ButtonLine;

@Component
public class CancelProductRequestButton implements ButtonLine {
    @Value(value = "${telegram.bot.api.commands.cancel-product-request}")
    private String cancelProductRequestCommand;

    @Override
    public SendMessage getButtonLine(long chatId) {
        return null;
    }

    @Override
    public String getCategory() {
        return cancelProductRequestCommand;
    }
}
