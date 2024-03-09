package ru.nsu.ooad.communicationboost.scenarios.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import ru.nsu.ooad.communicationboost.command.resolver.IResolverService;
import ru.nsu.ooad.communicationboost.dto.EventDto;
import ru.nsu.ooad.communicationboost.scenarios.exception.CommandExecuteException;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.IHandler;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.InterModularDto;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class IResolverServiceImpl implements IResolverService {

    private final Set<IHandler> handlers;

    @Override
    public ResponseEntity<?> executeHandler(EventDto event) {
        log.info("In resolver: Event: {} {} {}", event.getUsername(), event.getCommandName(), event.getMessageBody());
        IHandler handler = findRightHandler(event.getCommandName());
        return (event.getMessageBody() != null) ?
            handler.executeCommand(new InterModularDto(event.getUsername(), event.getMessageBody()))
            :
            ResponseEntity.ok(handler.getDescriptionArg());
    }

    private IHandler findRightHandler(String commandName) {
        return handlers.stream()
            .filter(handler -> commandName.equals(handler.getMetaInformation().commandName()))
            .findFirst()
            .orElseThrow(CommandExecuteException::new);
    }
}
