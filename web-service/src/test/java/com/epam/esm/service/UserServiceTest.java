package com.epam.esm.service;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.service.JwtService;
import com.epam.esm.service.impl.UserServiceImpl;
import com.epam.esm.util.hateoas.BaseHateoasAdder;
import com.epam.esm.util.mapper.TokenMapper;
import com.epam.esm.util.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private TokenMapper tokenMapper;

    @Mock
    private BaseHateoasAdder<UserDto> userHateoasAdder;
    @Mock
    private BaseHateoasAdder<TokenDto> tokenHateoasAdder;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void contextLoads() {
        assertNotNull(userService);
    }
}