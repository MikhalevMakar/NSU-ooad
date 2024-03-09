package ru.nsu.ooad.communicationboost.abilitycore.service.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.CommandRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Command;
import ru.nsu.ooad.communicationboost.abilitycore.dto.command.CommandUrlDto;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.exception.command.CommandNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.service.command.CommandService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Service(value = "defaultCommandService")
@RequiredArgsConstructor
@Primary
public class CommandServiceImpl implements CommandService {
    private final CommandRepository commandRepository;

    @Override
    public MessageResponse getCommandInfo(DtoMarker dtoMarker) {
        CommandUrlDto commandUrlDto = (CommandUrlDto) dtoMarker;
        Command command = commandRepository.findCommandByCommandUrl(commandUrlDto.getCommandUrl())
                .orElseThrow(() -> new CommandNotFoundException("Undefined command"));
        return new MessageResponse("Description: %s%nRole: %s".formatted(command.getCommandInfo(), command.getRequiredRole().toString()));
    }
}
