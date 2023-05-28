package com.epam.esm.service.impl;

import com.epam.esm.entity.Token;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.service.TokenService;
import com.epam.esm.util.enums.field.TokenField;
import com.epam.esm.util.enums.field.UserField;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;

    @Scheduled(fixedRate = 1000)
    public void updateExpiringTokens() {
        LocalDateTime fifteenMinutesBeforeNow =
                LocalDateTime.now().minusMinutes(15);

        for (Token token : tokenRepository
                .findByCreatedAtBefore(fifteenMinutesBeforeNow)) {
            token.setExpired(true);
            tokenRepository.save(token);
        }
    }

    @Override
    public Page<Token> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        return tokenRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Token findById(Long id) {
        CustomValidator.validateId(TokenField.ID, id);

        return tokenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Token.class, id));
    }

    @Override
    public Page<Token> findByUserIdAndPage(Long userId, int page, int size) {
        CustomPageValidator.validate(page, size);
        CustomValidator.validateId(UserField.ID, userId);

        return tokenRepository.findByUserId(userId, PageRequest.of(page, size));
    }
}
