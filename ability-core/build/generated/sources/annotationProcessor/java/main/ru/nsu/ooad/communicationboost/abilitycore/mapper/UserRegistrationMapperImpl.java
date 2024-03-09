package ru.nsu.ooad.communicationboost.abilitycore.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserContextDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserRegistrationDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-17T21:49:29+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserRegistrationMapperImpl implements UserRegistrationMapper {

    @Override
    public User mapToEntity(UserRegistrationDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( userDto.getUsername() );

        return user;
    }

    @Override
    public User mapToEntity(UserContextDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setEmail( userDto.getEmail() );
        user.setUsername( userDto.getUsername() );

        return user;
    }
}
