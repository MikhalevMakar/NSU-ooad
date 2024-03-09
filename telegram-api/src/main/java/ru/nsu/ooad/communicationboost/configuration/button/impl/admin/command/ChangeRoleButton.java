package ru.nsu.ooad.communicationboost.configuration.button.impl.admin.command;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.nsu.ooad.communicationboost.configuration.button.ButtonLine;

@Component
public class ChangeRoleButton implements ButtonLine {
    @Value(value = "${telegram.bot.api.commands.change-role}")
    private String changeRoleCommand;

    @Override
    public SendMessage getButtonLine(long chatId) {
        return null;
    }

    @Override
    public String getCategory() {
        return changeRoleCommand;
    }
}
