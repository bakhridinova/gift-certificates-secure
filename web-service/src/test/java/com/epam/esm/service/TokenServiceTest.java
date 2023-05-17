package com.epam.esm.service;

import com.epam.esm.repository.TokenRepository;
import com.epam.esm.service.impl.TokenServiceImpl;
import com.epam.esm.util.hateoas.impl.TokenHateoasAdderImpl;
import com.epam.esm.util.mapper.TokenMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class TokenServiceTest {
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private TokenMapper tokenMapper;
    @Mock
    private TokenHateoasAdderImpl tokenHateoasAdder;

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Test
    void contextLoads() {
        assertNotNull(tokenService);
    }
}