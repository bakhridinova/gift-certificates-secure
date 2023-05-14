package com.epam.esm.service.impl;

import com.epam.esm.dto.UserDto;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.UserService;
import com.epam.esm.util.hateoas.HateoasAdder;
import com.epam.esm.util.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final HateoasAdder<UserDto> userHateoasAdder;

    @Override
    public Page<UserDto> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> users = userMapper.mapEntitiyPageToEntityDtoPage(userRepository
                .findAll(pageable), userMapper);
        userHateoasAdder.addLinksToEntityPage(users);
        return users;
    }

    @Override
    public UserDto findById(Long id) {
        UserDto user = userMapper.toEntityDto(userRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(
                        "failed to find user by orderId " + id)));
        userHateoasAdder.addLinksToEntity(user);
        return user;
    }

    @Override
    public UserDto create(UserDto userDto) {
        userDto = userMapper.toEntityDto(userRepository
                .save(userMapper.toEntity(userDto)));

        userHateoasAdder.addLinksToEntity(userDto);
        return userDto;
    }
}
