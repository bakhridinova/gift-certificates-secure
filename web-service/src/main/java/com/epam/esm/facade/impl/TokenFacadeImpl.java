package com.epam.esm.facade.impl;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.facade.TokenFacade;
import com.epam.esm.service.TokenService;
import com.epam.esm.util.hateoas.TokenHateoasAdder;
import com.epam.esm.util.mapper.TokenMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenFacadeImpl implements TokenFacade {
    private final TokenHateoasAdder tokenHateoasAdder;
    private final TokenService tokenService;
    private final TokenMapper tokenMapper;
    @Override
    public Page<TokenDto> findAllByPage(int page, int size) {
        Page<TokenDto> tokens = tokenMapper.mapEntitiyPageToEntityDtoPage(
                tokenService.findAllByPage(page, size), tokenMapper);

        tokenHateoasAdder.addLinksToEntityPage(tokens);
        return tokens;
    }

    @Override
    public TokenDto findById(Long id) {
        TokenDto token = tokenMapper.toEntityDto(tokenService.findById(id));

        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }

    @Override
    public Page<TokenDto> findByUserIdAndPage(Long userId, int page, int size) {
        Page<TokenDto> tokens = tokenMapper.mapEntitiyPageToEntityDtoPage(
                tokenService.findByUserIdAndPage(userId, page, size), tokenMapper);

        tokenHateoasAdder.addLinksToEntityPage(tokens);
        return tokens;
    }
}
