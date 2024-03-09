package ru.nsu.ooad.communicationboost.configuration.button.impl.admin;

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
public class AdminButtonLine implements ButtonLine, SupportedRole {
    @Value(value = "${telegram.bot.api.commands.admin}")
    private String adminCommand;
    @Value(value = "${telegram.bot.api.messages.select-command}")
    private String messageForSelectCommand;
    @Value(value = "${telegram.bot.api.commands.block-user}")
    private String blockUserCommand;
    @Value(value = "${telegram.bot.api.commands.unblock-user}")
    private String unblockUserCommand;
    @Value(value = "${telegram.bot.api.commands.change-role}")
    private String changeRoleCommand;
    
    @Override
    public SendMessage getButtonLine(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(messageForSelectCommand);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        markupInline.setKeyboard(prepareAdminKeyboardButtons());
        message.setReplyMarkup(markupInline);
        return message;
    }

    @Override
    public String getCategory() {
        return adminCommand;
    }

    private List<List<InlineKeyboardButton>> prepareAdminKeyboardButtons() {
        InlineKeyboardButton blockUserKeyboardButton = new InlineKeyboardButton();
        blockUserKeyboardButton.setText(blockUserCommand);
        blockUserKeyboardButton.setCallbackData(blockUserKeyboardButton.getText());

        InlineKeyboardButton unblockUserKeyboardButton = new InlineKeyboardButton();
        unblockUserKeyboardButton.setText(unblockUserCommand);
        unblockUserKeyboardButton.setCallbackData(unblockUserKeyboardButton.getText());

        InlineKeyboardButton changeRoleKeyboardButton = new InlineKeyboardButton();
        changeRoleKeyboardButton.setText(changeRoleCommand);
        changeRoleKeyboardButton.setCallbackData(changeRoleKeyboardButton.getText());

        List<InlineKeyboardButton> firstLineButtons = new ArrayList<>();
        firstLineButtons.add(blockUserKeyboardButton);
        firstLineButtons.add(unblockUserKeyboardButton);

        List<InlineKeyboardButton> secondLineButtons = new ArrayList<>();
        secondLineButtons.add(changeRoleKeyboardButton);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        rowsInline.add(firstLineButtons);
        rowsInline.add(secondLineButtons);
        return rowsInline;
    }
}
