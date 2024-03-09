package ru.nsu.ooad.communicationboost.configuration.button.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nsu.ooad.communicationboost.configuration.button.ButtonLine;

import java.util.ArrayList;
import java.util.List;

@Component
public class RolesButtonLine implements ButtonLine {
    @Value(value = "${telegram.bot.api.commands.roles}")
    private String rolesCommand;
    @Value(value = "${telegram.bot.api.messages.roles}")
    private String messageForRoles;
    @Value(value = "${telegram.bot.api.commands.client}")
    private String clientCommand;
    @Value(value = "${telegram.bot.api.commands.employee}")
    private String employeeCommand;
    @Value(value = "${telegram.bot.api.commands.admin}")
    private String adminCommand;

    @Override
    public SendMessage getButtonLine(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageForRoles);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(prepareRolesKeyboardButtons());
        message.setReplyMarkup(markupInline);
        return message;
    }

    @Override
    public String getCategory() {
        return rolesCommand;
    }

    private List<List<InlineKeyboardButton>> prepareRolesKeyboardButtons() {
        InlineKeyboardButton clientKeyboardButton = new InlineKeyboardButton();
        clientKeyboardButton.setText(clientCommand);
        clientKeyboardButton.setCallbackData(clientKeyboardButton.getText());

        InlineKeyboardButton employeeKeyboardButton = new InlineKeyboardButton();
        employeeKeyboardButton.setText(employeeCommand);
        employeeKeyboardButton.setCallbackData(employeeKeyboardButton.getText());

        InlineKeyboardButton adminKeyboardButton = new InlineKeyboardButton();
        adminKeyboardButton.setText(adminCommand);
        adminKeyboardButton.setCallbackData(adminKeyboardButton.getText());

        List<InlineKeyboardButton> firstLineButtons = new ArrayList<>();
        firstLineButtons.add(clientKeyboardButton);
        firstLineButtons.add(employeeKeyboardButton);
        firstLineButtons.add(adminKeyboardButton);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstLineButtons);
        return rowsInline;
    }
}
