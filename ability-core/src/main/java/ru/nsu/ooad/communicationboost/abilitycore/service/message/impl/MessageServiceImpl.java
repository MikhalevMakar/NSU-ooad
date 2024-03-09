package ru.nsu.ooad.communicationboost.abilitycore.service.message.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.UserRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.User;
import ru.nsu.ooad.communicationboost.abilitycore.dto.message.MessageForEmailsDto;
import ru.nsu.ooad.communicationboost.abilitycore.dto.message.MessageForRolesDto;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.enums.Role;
import ru.nsu.ooad.communicationboost.abilitycore.exception.message.EmailNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.UserNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.service.message.MessageService;
import ru.nsu.ooad.communicationboost.emailapi.model.EmailMessage;
import ru.nsu.ooad.communicationboost.emailapi.service.EmailService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

import java.util.List;

@Slf4j
@Primary
@Service(value = "defaultMessageService")
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserRepository userRepository;

    private final EmailService emailService;

    //TODO: Возможно перенесем все в properties и будем брать сообщение для команды
    @Value("${api.mail.username}")
    private String emailSender;

    @Value("${api.mail.status.response.successful}")
    private String successfulSendMessage;

    @Value("${api.mail.status.response.failed}")
    private String failedSendMessage;

    @Override
    @Transactional(readOnly = true)
    public MessageResponse sendMessageByEmail(DtoMarker messageDto) {
        MessageForEmailsDto messageForEmailsDto = (MessageForEmailsDto) messageDto;

        this.isCorrectUser(messageForEmailsDto.getUsername());

        return this.sendMessageByEmails(messageForEmailsDto.getEmailDtoList(),
                                        messageForEmailsDto.getMessageBody());
    }

    @Override
    @Transactional(readOnly = true)
    public MessageResponse sendMessageByRole(DtoMarker messageDto) {
        MessageForRolesDto messageForRolesDto = (MessageForRolesDto) messageDto;

        this.isCorrectUser(messageForRolesDto.getUsername());

        Role role = Role.valueOf(messageForRolesDto.getRole().toUpperCase().trim());

        List<String> emails = userRepository.findAllByRole(role)
                                                 .stream()
                                                 .map(User::getEmail)
                                                 .toList();

        return this.sendMessageByEmails(emails, messageForRolesDto.getMessageBody());
    }

    private MessageResponse sendMessageByEmails(List<String> emails, String message) {
        try {
            this.isCorrectEmails(emails);
        } catch(EmailNotFoundException ex) {
            log.warn("email not found exception: ", ex);
            return new MessageResponse(failedSendMessage);
        }

        emails.forEach(emailDto ->
            this.emailService.sendMessage(EmailMessage
                .builder()
                .sender(emailSender)
                .recipient(emailDto)
                .text(message)
                .build()));

        return new MessageResponse(successfulSendMessage);
    }

    private void isCorrectEmails(List<String> emailDtoList) {
        for (String emailDto : emailDtoList) {
            if (!userRepository.existsUserByEmail(emailDto)) {
                throw new EmailNotFoundException(emailDto); // TODO: add to list and handle later
            }
        }
    }

    private void isCorrectUser(String username) {
        if(!userRepository.existsByUsername(username))
            throw new UserNotFoundException("User not found");
    }
}
