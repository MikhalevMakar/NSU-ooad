package ru.nsu.ooad.communicationboost.command.resolver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.nsu.ooad.communicationboost.dto.EventDto;

@Service
public interface IResolverService {
    ResponseEntity<?> executeHandler(EventDto event);
}