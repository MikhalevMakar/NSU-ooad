package ru.nsu.ooad.communicationboost.dto;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
@Setter
public class EventDto {

    private String username;

    private String commandName;

    private String messageBody;

    public static EventDto parseUpdate(Update update) {
        EventDto eventDto = new EventDto();

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            eventDto.setUsername(callbackQuery.getFrom().getUserName());
            eventDto.setCommandName(callbackQuery.getData());
        } else if (update.hasMessage() && update.getMessage().hasText()) {
            eventDto.setUsername(update.getMessage().getFrom().getUserName());
            eventDto.setMessageBody(update.getMessage().getText());
        }
        return eventDto;
    }
}
