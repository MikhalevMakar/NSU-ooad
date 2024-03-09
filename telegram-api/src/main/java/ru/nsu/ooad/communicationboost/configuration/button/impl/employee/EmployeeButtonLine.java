package ru.nsu.ooad.communicationboost.configuration.button.impl.employee;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.nsu.ooad.communicationboost.configuration.button.ButtonLine;
import ru.nsu.ooad.communicationboost.configuration.roles.SupportedRole;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeButtonLine implements ButtonLine, SupportedRole {
    @Value(value = "${telegram.bot.api.commands.employee}")
    private String employeeCommand;
    @Value(value = "${telegram.bot.api.messages.select-command}")
    private String messageForSelectCommand;
    @Value(value = "${telegram.bot.api.commands.send-message-by-role}")
    private String sendMessageByRoleCommand;
    @Value(value = "${telegram.bot.api.commands.send-message-by-email}")
    private String sendMessageByEmailCommand;
    @Value(value = "${telegram.bot.api.commands.cancel-product-request}")
    private String cancelProductRequestCommand;
    @Value(value = "${telegram.bot.api.commands.accept-product-request}")
    private String acceptProductRequestCommand;

    @Override
    public SendMessage getButtonLine(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageForSelectCommand);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(prepareEmployeeKeyboardButtons());
        message.setReplyMarkup(markupInline);
        return message;
    }

    @Override
    public String getCategory() {
        return employeeCommand;
    }

    private List<List<InlineKeyboardButton>> prepareEmployeeKeyboardButtons() {
        InlineKeyboardButton sendMessageByRoleKeyboardButton = new InlineKeyboardButton();
        sendMessageByRoleKeyboardButton.setText(sendMessageByRoleCommand);
        sendMessageByRoleKeyboardButton.setCallbackData(sendMessageByRoleKeyboardButton.getText());

        InlineKeyboardButton sendMessageByEmailKeyboardButton = new InlineKeyboardButton();
        sendMessageByEmailKeyboardButton.setText(sendMessageByEmailCommand);
        sendMessageByEmailKeyboardButton.setCallbackData(sendMessageByEmailKeyboardButton.getText());

        InlineKeyboardButton cancelProductRequestKeyboardButton = new InlineKeyboardButton();
        cancelProductRequestKeyboardButton.setText(cancelProductRequestCommand);
        cancelProductRequestKeyboardButton.setCallbackData(cancelProductRequestKeyboardButton.getText());

        InlineKeyboardButton acceptProductRequestKeyboardButton = new InlineKeyboardButton();
        acceptProductRequestKeyboardButton.setText(acceptProductRequestCommand);
        acceptProductRequestKeyboardButton.setCallbackData(acceptProductRequestKeyboardButton.getText());

        List<InlineKeyboardButton> firstLineButtons = new ArrayList<>();
        firstLineButtons.add(sendMessageByRoleKeyboardButton);
        firstLineButtons.add(sendMessageByEmailKeyboardButton);

        List<InlineKeyboardButton> secondLineButtons = new ArrayList<>();
        secondLineButtons.add(cancelProductRequestKeyboardButton);
        secondLineButtons.add(acceptProductRequestKeyboardButton);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstLineButtons);
        rowsInline.add(secondLineButtons);
        return rowsInline;
    }
}
