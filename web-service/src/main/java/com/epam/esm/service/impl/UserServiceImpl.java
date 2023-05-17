package com.epam.esm.service.impl;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.Token;
import com.epam.esm.entity.User;
import com.epam.esm.enums.TokenType;
import com.epam.esm.exception.CustomEntityAlreadyExistsException;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.basic.CustomBasicAuthentication;
import com.epam.esm.security.authentication.service.CustomUserDetails;
import com.epam.esm.security.authentication.service.JwtService;
import com.epam.esm.service.UserService;
import com.epam.esm.util.enums.field.UserField;
import com.epam.esm.util.hateoas.TokenHateoasAdder;
import com.epam.esm.util.hateoas.UserHateoasAdder;
import com.epam.esm.util.mapper.TokenMapper;
import com.epam.esm.util.mapper.UserMapper;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomUserValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserHateoasAdder userHateoasAdder;
    private final TokenRepository tokenRepository;
    private final TokenMapper tokenMapper;
    private final TokenHateoasAdder tokenHateoasAdder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Page<UserDto> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> users = userMapper.mapEntitiyPageToEntityDtoPage(userRepository
                .findAll(pageable), userMapper);

        userHateoasAdder.addLinksToEntityPage(users);
        return users;
    }

    @Override
    public UserDto findById(Long id) {
        CustomValidator.validateId(UserField.ID, id);

        UserDto user = userMapper.toEntityDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(User.class, id)));

        userHateoasAdder.addLinksToEntity(user);
        return user;
    }

    @Override
    @Transactional
    public TokenDto singUp(UserDto userDto) {
        CustomUserValidator.validate(userDto);

        userRepository.findByUsername(userDto.getUsername()).ifPresent((u) -> {
            throw new CustomEntityAlreadyExistsException("username already taken");
        });

        User user = userRepository
                .save(userMapper.toEntity(userDto));

        TokenDto token = getTokenForUser(userDto, user);
        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }

    @Override
    public TokenDto signIn(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new CustomEntityNotFoundException("username does not exist"));

        TokenDto token = getTokenForUser(userDto, user);
        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }

    private TokenDto getTokenForUser(UserDto userDto, User user) {
        Authentication authentication = authenticationManager.authenticate(
                new CustomBasicAuthentication(false, userDto.getUsername(), userDto.getPassword()));

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        CustomUserDetails userDetails = new CustomUserDetails(user);
        Token token = Token.builder()
                .accessToken(jwtService.generateToken(userDetails))
                .type(TokenType.BEARER).user(user)
                .build();
        tokenRepository.save(token);
        return tokenMapper.toEntityDto(token);
    }
}
