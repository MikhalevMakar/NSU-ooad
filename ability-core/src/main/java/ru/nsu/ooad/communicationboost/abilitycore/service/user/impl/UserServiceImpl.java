package ru.nsu.ooad.communicationboost.abilitycore.service.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.UserRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Context;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.dto.security.ResponseVerification;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserContextDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserNewRoleDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserRegistrationDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UsernameDto;
import ru.nsu.ooad.communicationboost.abilitycore.enums.Role;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.UserCreationException;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.UserNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.mapper.UserRegistrationMapper;
import ru.nsu.ooad.communicationboost.abilitycore.properitestgmessage.MessageTg;
import ru.nsu.ooad.communicationboost.abilitycore.security.service.SecurityService;
import ru.nsu.ooad.communicationboost.abilitycore.service.context.ContextService;
import ru.nsu.ooad.communicationboost.abilitycore.service.user.UserService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Primary
@Service(value = "defaultUserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserRegistrationMapper userRegistrationMapper;

    private final ContextService contextService;

    private final SecurityService securityService;

    private final MessageTg messageTg;

    @Value("${api.user.status.response.unblock}")
    private String successfulUnblockUser;

    @Value("${api.user.status.response.block}")
    private String successfulBlockUser;

    @Value("${api.user.status.response.successful-authorization}")
    private String authorizedMessage;

    @Value("${api.user.status.response.change-role}")
    private String successfulChangeRole;

    @Override
    @Transactional
    public MessageResponse registrationUser(DtoMarker userDto) {
        UserRegistrationDto dto = (UserRegistrationDto) userDto;
        Context context = this.contextService.findContext(dto.getUsername());
        ResponseVerification response = securityService.verificationCode(dto.getUsername(), dto.getCode());

        if(!response.status()) {
            context.setAttempts(context.getAttempts() + INCREASE);
            return new MessageResponse(messageTg.getFailedConfirmationCode());
        }

        if (userRepository.existsUserByEmail(context.getEmail()))
            return new MessageResponse(authorizedMessage);


        User user = userRegistrationMapper.mapToEntity(dto);
        user.setEmail(context.getEmail());
        userRepository.save(user);

        this.contextService.deleteContext(user.getUsername());
        return new MessageResponse(messageTg.getSuccessfullyConfirmationCode());
    }

    @Override
    @Transactional
    public MessageResponse changeRoleOfUser(DtoMarker userDto) {
        UserNewRoleDto userNewRoleDto = (UserNewRoleDto) userDto;
        User user = userRepository.findUserByUsername(userNewRoleDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Role newRole = Role.valueOf(userNewRoleDto.getRole().toUpperCase().trim());
        user.setRole(newRole);
        userRepository.save(user);

        return new MessageResponse(successfulChangeRole);
    }

    @Override
    @Transactional
    public MessageResponse blockUser(DtoMarker userDto) {
        UsernameDto usernameDto = (UsernameDto) userDto;
        User user = userRepository.findUserByUsername(usernameDto.getUsername())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        Context context = this.contextService.findOrCreateContext(user.getEmail(), user.getUsername());
        context.setActive(false);

        this.contextService.save(context);

        userRepository.delete(user);
        return new MessageResponse(successfulBlockUser);
    }

    @Override
    @Transactional
    public MessageResponse unblockUser(DtoMarker dtoMarker) {
        UserContextDto userDto = (UserContextDto) dtoMarker;

        this.contextService.deleteContext(userDto.getUsername());

        User user = this.userRegistrationMapper.mapToEntity(userDto);
        userRepository.save(user);
        return new MessageResponse(successfulUnblockUser);
    }
}
