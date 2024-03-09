package ru.nsu.ooad.communicationboost.abilitycore.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UsernameDto;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-19T13:10:08+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.4.jar, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UsernameMapperImpl implements UsernameMapper {

    @Override
    public UsernameDto mapToUsernameDto(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;

        username = user.getUsername();

        UsernameDto usernameDto = new UsernameDto( username );

        return usernameDto;
    }
}
