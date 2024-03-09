package ru.nsu.ooad.communicationboost.abilitycore.service.context.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.dao.ContextRepository;
import ru.nsu.ooad.communicationboost.abilitycore.dbmodel.entity.Context;
import ru.nsu.ooad.communicationboost.dto.response.MessageResponse;
import ru.nsu.ooad.communicationboost.abilitycore.dto.user.UserContextDto;
import ru.nsu.ooad.communicationboost.abilitycore.exception.user.ContextNotFoundException;
import ru.nsu.ooad.communicationboost.abilitycore.properitestgmessage.MessageTg;
import ru.nsu.ooad.communicationboost.abilitycore.security.service.SecurityService;
import ru.nsu.ooad.communicationboost.abilitycore.service.context.ContextService;
import ru.nsu.ooad.communicationboost.emailapi.model.EmailMessage;
import ru.nsu.ooad.communicationboost.emailapi.service.EmailService;
import ru.nsu.ooad.communicationboost.scenarios.modelcontract.messagedto.DtoMarker;

@Primary
@Service
@RequiredArgsConstructor
public class ContextServiceImpl implements ContextService {

    private final ContextRepository contextRepository;

    private final EmailService emailService;

    private final MessageTg messageTg;

    private final SecurityService securityService;

    @Value("${api.mail.username}")
    private String emailSender;

    @Value("${api.context.status.response.successful-send-code}")
    private String successfulSendCodeConfirmation;

    @Value("${api.context.status.response.block-user}")
    private String blockUserMessage;

    @Override
    public Context findOrCreateContext(String email, String username) {
        return contextRepository.findByUsername(username)
                 .orElse(new Context()
                                .setEmail(email)
                                .setUsername(username));
    }

    @Override
    public Context findContext(String username) {
        return contextRepository.findByUsername(username)
                            .orElseThrow(() -> new ContextNotFoundException(username));
    }

    @Override
    public void deleteContext(String username) {
        contextRepository.deleteByUsername(username);
    }

    @Override
    public void save(Context context) {
        this.contextRepository.save(context);
    }

    @Override
    public void blockContext(String username) {
        Context context = findContext(username);
        context.setActive(false);
        this.contextRepository.save(context);
    }

    @Override
    @Transactional
    public MessageResponse createContext(DtoMarker userDto) {
        UserContextDto dto = (UserContextDto) userDto;

        Context context = this.findOrCreateContext(dto.getEmail(), dto.getUsername());

        if(context.getAttempts() == MAX_ATTEMPTS_REG) {
            this.blockContext(context.getUsername());
            return new MessageResponse(blockUserMessage);
        }

        context.setActive(true)
               .setAttempts(context.getAttempts() + INCREASE);
        contextRepository.save(context);

        this.emailService.sendMessage(new EmailMessage(emailSender, dto.getEmail(),
            messageTg.getCodeConfirmation() + securityService.getCodeByUser(dto.getUsername())));

        return new MessageResponse(successfulSendCodeConfirmation);
    }
}
