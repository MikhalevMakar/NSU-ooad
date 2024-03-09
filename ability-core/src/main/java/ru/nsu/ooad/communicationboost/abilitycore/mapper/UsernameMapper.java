package ru.nsu.ooad.communicationboost.abilitycore.mapper;

import org.mapstruct.Mapper;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UsernameDto;

@Mapper
public interface UsernameMapper {
    UsernameDto mapToUsernameDto(User user);
}
