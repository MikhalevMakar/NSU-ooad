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
public class StartButtonLine implements ButtonLine {
    @Value(value = "${telegram.bot.api.commands.start}")
    private String startCommand;
    @Value(value = "${telegram.bot.api.messages.start}")
    private String messageForStart;

    @Override
    public SendMessage getButtonLine(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageForStart);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(prepareStartKeyboardButtons());
        message.setReplyMarkup(markupInline);
        return message;
    }

    @Override
    public String getCategory() {
        return startCommand;
    }

    private List<List<InlineKeyboardButton>> prepareStartKeyboardButtons() {
        InlineKeyboardButton startKeyboardButton = new InlineKeyboardButton();
        startKeyboardButton.setText(startCommand);
        startKeyboardButton.setCallbackData(startKeyboardButton.getText());

        List<InlineKeyboardButton> firstLineButtons = new ArrayList<>();
        firstLineButtons.add(startKeyboardButton);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstLineButtons);
        return rowsInline;
    }
}
