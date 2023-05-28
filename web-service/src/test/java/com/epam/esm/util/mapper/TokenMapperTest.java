package com.epam.esm.util.mapper;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.entity.Token;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TokenMapperTest {
    private final TokenMapper tokenMapper;

    public TokenMapperTest() {
        tokenMapper = new TokenMapperImpl();
    }

    private static final Token NULL_TOKEN =
           Token.builder().build();
    private static final TokenDto NULL_TOKEN_DTO =
            TokenDto.builder().build();

    private static final Token NON_NULL_TOKEN =
            Token.builder().id(0L).build();
    private static final TokenDto NON_NULL_TOKEN_DTO =
            TokenDto.builder().id(0L).build();

    @Test
    void shouldMapToEntityCorrectlyTest() {
        assertNull(tokenMapper.toEntity(null));
        assertEquals(NULL_TOKEN, tokenMapper.toEntity(NULL_TOKEN_DTO));
        assertEquals(NON_NULL_TOKEN, tokenMapper.toEntity(NON_NULL_TOKEN_DTO));
    }


    @Test
    void shouldMapToEntityDtoCorrectlyTest() {
        assertNull(tokenMapper.toEntityDto(null));
        assertEquals(NULL_TOKEN_DTO, tokenMapper.toEntityDto(NULL_TOKEN));
        assertEquals(NON_NULL_TOKEN_DTO, tokenMapper.toEntityDto(NON_NULL_TOKEN));
    }
}