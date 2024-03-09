package ru.nsu.ooad.communicationboost.configuration.button.impl.client;

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
public class ClientButtonLine implements ButtonLine, SupportedRole {
    @Value(value = "${telegram.bot.api.commands.client}")
    private String clientCommand;
    @Value(value = "${telegram.bot.api.messages.select-command}")
    private String messageForSelectCommand;
    @Value(value = "${telegram.bot.api.commands.get-product-info}")
    private String getProductInfoCommand;
    @Value(value = "${telegram.bot.api.commands.view-product-requests}")
    private String viewProductRequestsCommands;
    @Value(value = "${telegram.bot.api.commands.send-product-request}")
    private String sendProductRequestCommand;
    @Value(value = "${telegram.bot.api.commands.help}")
    private String helpCommand;

    @Override
    public SendMessage getButtonLine(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageForSelectCommand);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(prepareClientKeyboardButtons());
        message.setReplyMarkup(markupInline);
        return message;
    }

    @Override
    public String getCategory() {
        return clientCommand;
    }

    private List<List<InlineKeyboardButton>> prepareClientKeyboardButtons() {
        InlineKeyboardButton getProductInfoKeyboardButton = new InlineKeyboardButton();
        getProductInfoKeyboardButton.setText(getProductInfoCommand);
        getProductInfoKeyboardButton.setCallbackData(getProductInfoKeyboardButton.getText());

        InlineKeyboardButton viewProductRequestsKeyboardButton = new InlineKeyboardButton();
        viewProductRequestsKeyboardButton.setText(viewProductRequestsCommands);
        viewProductRequestsKeyboardButton.setCallbackData(viewProductRequestsKeyboardButton.getText());

        InlineKeyboardButton sendProductRequestKeyboardButton = new InlineKeyboardButton();
        sendProductRequestKeyboardButton.setText(sendProductRequestCommand);
        sendProductRequestKeyboardButton.setCallbackData(sendProductRequestKeyboardButton.getText());

        InlineKeyboardButton helpKeyboardButton = new InlineKeyboardButton();
        helpKeyboardButton.setText(helpCommand);
        helpKeyboardButton.setCallbackData(helpKeyboardButton.getText());

        List<InlineKeyboardButton> firstLineButtons = new ArrayList<>();
        firstLineButtons.add(getProductInfoKeyboardButton);
        firstLineButtons.add(viewProductRequestsKeyboardButton);

        List<InlineKeyboardButton> secondLineButtons = new ArrayList<>();
        secondLineButtons.add(sendProductRequestKeyboardButton);
        secondLineButtons.add(helpKeyboardButton);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstLineButtons);
        rowsInline.add(secondLineButtons);
        return rowsInline;
    }
}
