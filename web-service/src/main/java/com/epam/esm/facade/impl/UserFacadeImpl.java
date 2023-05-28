package com.epam.esm.facade.impl;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserCredentialsDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.facade.UserFacade;
import com.epam.esm.service.UserService;
import com.epam.esm.util.hateoas.TokenHateoasAdder;
import com.epam.esm.util.hateoas.UserHateoasAdder;
import com.epam.esm.util.mapper.TokenMapper;
import com.epam.esm.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final TokenHateoasAdder tokenHateoasAdder;
    private final UserHateoasAdder userHateoasAdder;
    private final UserService userService;
    private final TokenMapper tokenMapper;
    private final UserMapper userMapper;

    @Override
    public Page<UserDto> findAllByPage(int page, int size) {
        Page<UserDto> users = userMapper.mapEntitiyPageToEntityDtoPage(
                userService.findAllByPage(page, size), userMapper);

        userHateoasAdder.addLinksToEntityPage(users);
        return users;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto user = userMapper.toEntityDto(userService.findById(id));

        userHateoasAdder.addLinksToEntity(user);
        return user;
    }

    @Override
    public TokenDto singUp(UserCredentialsDto userCredentials) {
        TokenDto token = tokenMapper.toEntityDto(userService.singUp(
                userMapper.toEntity(userCredentials)));

        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }

    @Override
    public TokenDto signIn(UserCredentialsDto userCredentials) {
        TokenDto token = tokenMapper.toEntityDto(userService.signIn(
                userMapper.toEntity(userCredentials)));

        tokenHateoasAdder.addLinksToEntity(token);
        return token;
    }
}
