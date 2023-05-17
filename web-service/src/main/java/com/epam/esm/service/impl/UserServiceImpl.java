package com.epam.esm.service.impl;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.CustomEntityAlreadyExistsException;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.CustomBasicAuthentication;
import com.epam.esm.security.authentication.provider.CustomUserDetails;
import com.epam.esm.security.authentication.provider.JwtService;
import com.epam.esm.service.UserService;
import com.epam.esm.util.enums.field.UserField;
import com.epam.esm.util.hateoas.HateoasAdder;
import com.epam.esm.util.mapper.UserMapper;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomUserValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HateoasAdder<UserDto> userHateoasAdder;
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
    public String singUp(UserDto user) {
        CustomUserValidator.validate(user);

        userRepository.findByUsername(user.getUsername()).ifPresent((u) -> {
            throw new CustomEntityAlreadyExistsException("username already taken");
        });

        return jwtService.generateToken(new CustomUserDetails(userRepository.save(userMapper.toEntity(user))));
    }

    @Override
    public String signIn(UserDto user) {
        authenticationManager.authenticate(
                new CustomBasicAuthentication(false, user.getUsername() + ":" + user.getPassword()));

        return jwtService.generateToken(new CustomUserDetails(userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new CustomEntityNotFoundException("username does not exist"))));
    }
}
