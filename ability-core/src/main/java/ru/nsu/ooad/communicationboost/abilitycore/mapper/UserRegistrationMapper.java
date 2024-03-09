package ru.nsu.ooad.communicationboost.abilitycore.mapper;

import org.mapstruct.Mapper;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserContextDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserRegistrationDto;

@Mapper
public interface UserRegistrationMapper {
    User mapToEntity(UserRegistrationDto userDto);

    User mapToEntity(UserContextDto userDto);
}
