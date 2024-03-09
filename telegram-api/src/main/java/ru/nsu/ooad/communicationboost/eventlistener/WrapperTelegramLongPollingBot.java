package ru.nsu.ooad.communicationboost.eventlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.nsu.ooad.communicationboost.command.CommandProcessingStatus;
import ru.nsu.ooad.communicationboost.command.resolver.IResolverService;
import ru.nsu.ooad.communicationboost.command.stage.CommandStage;
import ru.nsu.ooad.communicationboost.command.stage.StartStage;
import ru.nsu.ooad.communicationboost.configuration.BotConfiguration;
import ru.nsu.ooad.communicationboost.configuration.button.ButtonLine;
import ru.nsu.ooad.communicationboost.configuration.roles.SupportedRole;
import ru.nsu.ooad.communicationboost.dto.EventDto;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.dto.response.ResponseDescriptionArg;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Configuration
public class WrapperTelegramLongPollingBot extends TelegramLongPollingBot {
    private final BotConfiguration botConfiguration;
    private final Set<ButtonLine> buttonLines;
    private final Set<SupportedRole> supportedRoles;
    private final Map<String, StartStage> startedStatuses = new HashMap<>();
    private final Map<String, CommandProcessingStatus> processingUserCommands = new HashMap<>();
    private final Map<String, SendMessage> selectedRoles = new HashMap<>();
    private final IResolverService resolverService;

    @Value(value = "${telegram.bot.api.commands.start}")
    private String startCommand;
    @Value(value = "${telegram.bot.api.commands.roles}")
    private String rolesCommand;
    @Value(value = "${telegram.bot.api.commands.create}")
    private String createCommand;
    @Value(value = "${telegram.bot.api.messages.enter-email}")
    private String messageForEmailRequest;
    @Value(value = "${telegram.bot.api.messages.code-generation}")
    private String messageForCodeGeneration;
    @Value(value = "${telegram.bot.api.messages.successful-authorization}")
    private String messageForSuccessfulAuthorization;

    @Autowired
    public WrapperTelegramLongPollingBot(
            BotConfiguration botConfiguration,
            Set<ButtonLine> buttonLines,
            Set<SupportedRole> supportedRoles,
            IResolverService resolverService
    ) {
        super(botConfiguration.getToken());
        this.botConfiguration = botConfiguration;
        this.buttonLines = buttonLines;
        this.supportedRoles = supportedRoles;
        this.resolverService = resolverService;
        log.info("All dependencies for WrapperTelegramLongPollingBot injected");
        startedStatuses.put(botConfiguration.getUsername(), StartStage.FINISHED);
    }

    @Override
    public void onUpdateReceived(Update update) {
        EventDto eventDto = EventDto.parseUpdate(update);
        log.info("Received new event: {}, {}, {}", eventDto.getUsername(), eventDto.getCommandName(), eventDto.getMessageBody());
        if (update.hasMessage()) {
            handleCommonMessage(update.getMessage(), eventDto);
        } else if (update.hasCallbackQuery()) {
            try {
                handleCallbackQuery(update.getCallbackQuery(), eventDto);
            } catch (TelegramApiException exception) {
                throw new IllegalStateException(exception);
            }
        }
    }

    private void handleCommonMessage(Message message, EventDto eventDto) {
        try {
            if (checkIfFirstRequestFromUser(message)) {
                return;
            }
            SendMessage response = new SendMessage();
            response.setChatId(message.getChatId());

            String username = message.getFrom().getUserName();
            switch (startedStatuses.get(username)) {
                case WAITING_EMAIL_ADDRESS -> {
                    log.info("Waiting email address from {}", username);

                    eventDto.setCommandName(startCommand);
                    ResponseEntity<?> handlerResponse = resolverService.executeHandler(eventDto);
                    log.info("Response body: {}", handlerResponse.toString()); // TODO: handle response

                    startedStatuses.put(username, StartStage.WAITING_CODE);
                    response.setText(messageForCodeGeneration);
                    execute(response);
                    return;
                }
                case WAITING_CODE -> {
                    log.info("Waiting authorization code from {}", username);

                    eventDto.setCommandName(createCommand);
                    ResponseEntity<?> handlerResponse = resolverService.executeHandler(eventDto);
                    log.info("Response body: {}", handlerResponse.toString()); // TODO: handle response

                    MessageResponse responseBody = (MessageResponse) handlerResponse.getBody();
                    response.setText(responseBody.message());
                    execute(response);

                    startedStatuses.put(username, StartStage.FINISHED);
                    ButtonLine buttonLine = findSuitableButtonLine(rolesCommand);
                    execute(buttonLine.getButtonLine(message.getChatId()));
                    return;
                }
                default -> {
                    break;
                }
            }
            CommandProcessingStatus commandProcessingStatus = processingUserCommands.get(username);
            switch (commandProcessingStatus.getCommandStage()) {
                case COMMAND_BUTTON_PRESSED -> {
                    log.info("User {} pressed button for command {}", username, commandProcessingStatus.getCommandName());
                    eventDto.setCommandName(commandProcessingStatus.getCommandName());
                    eventDto.setMessageBody(null);
                    ResponseEntity<?> handlerResponse = resolverService.executeHandler(eventDto);
                    if (Objects.isNull(handlerResponse.getBody())) {
                        eventDto.setMessageBody("STUB MESSAGE"); // FIXME
                        handlerResponse = resolverService.executeHandler(eventDto);
                        log.info("Command without args response: {}", handlerResponse.toString());

                        MessageResponse responseBody = (MessageResponse) handlerResponse.getBody();
                        response.setText(responseBody.message());
                        execute(response);
                        commandProcessingStatus.setCommandStage(CommandStage.WAITING_NEW_COMMAND);
                        return;
                    }
                    log.info("Required parameters: {}", handlerResponse);

                    ResponseDescriptionArg responseBody = (ResponseDescriptionArg) handlerResponse.getBody();
                    response.setText(responseBody.message());
                    execute(response);

                    commandProcessingStatus.setCommandStage(CommandStage.WAITING_PARAMETERS_FOR_COMMAND);
                }
                case WAITING_PARAMETERS_FOR_COMMAND -> {
                    log.info("Waiting parameters for command {} from {}", commandProcessingStatus.getCommandName(), username);
                    eventDto.setCommandName(commandProcessingStatus.getCommandName());
                    ResponseEntity<?> handlerResponse = resolverService.executeHandler(eventDto);
                    log.info("Command response: {}", handlerResponse.toString());

                    MessageResponse responseBody = (MessageResponse) handlerResponse.getBody();
                    response.setText(responseBody.message());
                    execute(response);

                    commandProcessingStatus.setCommandStage(CommandStage.WAITING_NEW_COMMAND);
                    execute(selectedRoles.get(username));
                }
                default -> {
                    log.info("Waiting new command from {}", username);
                }
            }
        } catch (TelegramApiException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private void handleCallbackQuery(CallbackQuery callbackQuery, EventDto eventDto) throws TelegramApiException {
        Message receivedMessage = callbackQuery.getMessage();
        if (checkIfFirstRequestFromUser(receivedMessage)) {
            return;
        }
        if (callbackQuery.getData().equals(startCommand)) {
            SendMessage response = new SendMessage();
            response.setChatId(receivedMessage.getChatId());
            response.setText(messageForEmailRequest);
            execute(response);
            return;
        }
        ButtonLine buttonLine = findSuitableButtonLine(callbackQuery.getData());
        if (Objects.nonNull(buttonLine.getButtonLine(receivedMessage.getChatId()))) {
            execute(buttonLine.getButtonLine(receivedMessage.getChatId()));
        }
        if (isSelectionRoleCommand(callbackQuery.getData())) {
            selectedRoles.put(eventDto.getUsername(), buttonLine.getButtonLine(receivedMessage.getChatId()));
        }

        log.info("Add command {} to map from user: {}", callbackQuery.getData(), eventDto.getUsername());
        processingUserCommands.put(eventDto.getUsername(), new CommandProcessingStatus(
                        callbackQuery.getData(),
                        CommandStage.COMMAND_BUTTON_PRESSED
                )
        );
        receivedMessage.getFrom().setUserName(eventDto.getUsername());
        handleCommonMessage(receivedMessage, eventDto);
    }

    @Override
    public String getBotUsername() {
        return botConfiguration.getUsername();
    }

    @Bean
    public TelegramBotsApi botConnect() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
        log.info("Bot started!");
        return botsApi;
    }

    private boolean checkIfFirstRequestFromUser(Message message) throws TelegramApiException {
        if (!isUsernameInStartedStatuses(message.getFrom().getUserName())) {
            ButtonLine buttonLine = findSuitableButtonLine(startCommand);
            execute(buttonLine.getButtonLine(message.getChatId()));
            return true;
        }
        return false;
    }

    private ButtonLine findSuitableButtonLine(String callbackQueryData) {
        for (ButtonLine buttonLine : buttonLines) {
            if (callbackQueryData.equals(buttonLine.getCategory())) {
                return buttonLine;
            }
        }
        throw new IllegalArgumentException("Button line not found!");
    }

    private boolean isUsernameInStartedStatuses(String username) {
        if (startedStatuses.containsKey(username)) {
            return true;
        }
        startedStatuses.put(username, StartStage.WAITING_EMAIL_ADDRESS);
        log.info("Username {} added!", username);
        return false;
    }

    private boolean isSelectionRoleCommand(String commandName) {
        for (SupportedRole supportedRole : supportedRoles) {
            if (supportedRole.getCategory().equals(commandName)) {
                return true;
            }
        }
        return false;
    }
}
