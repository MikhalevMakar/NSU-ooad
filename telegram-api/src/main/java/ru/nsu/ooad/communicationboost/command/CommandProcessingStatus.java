package ru.nsu.ooad.communicationboost.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ooad.communicationboost.command.stage.CommandStage;

@Getter
@Setter
@AllArgsConstructor
public class CommandProcessingStatus {
    private String commandName;
    private CommandStage commandStage;
}
