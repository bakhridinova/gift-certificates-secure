package com.epam.esm.service.impl;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Token;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.service.TokenService;
import com.epam.esm.util.enums.field.CertificateField;
import com.epam.esm.util.hateoas.impl.TokenHateoasAdder;
import com.epam.esm.util.mapper.TokenMapper;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;
    private final TokenHateoasAdder tokenHateoasAdder;

    @Scheduled(fixedRate = 1000)
    public void updateExpiringTokens() {
        LocalDateTime fiveMinutesBeforeNow =
                LocalDateTime.now().minusMinutes(15);

        for (Token token : tokenRepository
                .findByCreatedAtBefore(fiveMinutesBeforeNow)) {
            token.setExpired(true);
        }
    }

    @Override
    public Page<TokenDto> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<TokenDto> tokens = tokenMapper.mapEntitiyPageToEntityDtoPage(tokenRepository
                .findAll(pageable), tokenMapper);

        tokenHateoasAdder.addLinksToEntityPage(tokens);
        return tokens;
    }

    @Override
    public TokenDto findById(Long id) {
        CustomValidator.validateId(CertificateField.ID, id);

        TokenDto token = tokenMapper.toEntityDto(tokenRepository
                .findById(id).orElseThrow(() -> new CustomEntityNotFoundException(Certificate.class, id)));

        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }

    @Override
    public Page<TokenDto> findByUserIdAndPage(Long userId, int page, int size) {
        CustomPageValidator.validate(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<TokenDto> tokens = tokenMapper.mapEntitiyPageToEntityDtoPage(tokenRepository
                .findByUserId(userId, pageable), tokenMapper);

        tokenHateoasAdder.addLinksToEntityPage(tokens);
        return tokens;
    }
}
