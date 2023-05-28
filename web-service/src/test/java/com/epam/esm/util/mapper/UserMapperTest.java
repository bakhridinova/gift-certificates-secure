package com.epam.esm.util.mapper;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserMapperTest {
    private final UserMapper userMapper;

    public UserMapperTest() {
        userMapper = new UserMapperImpl();
    }

    private static final User NULL_USER =
            User.builder().build();
    private static final UserDto NULL_USER_DTO =
           UserDto.builder().build();

    private static final User NON_NULL_USER =
            User.builder().id(0L).build();
    private static final UserDto NON_NULL_USER_DTO =
            UserDto.builder().id(0L).build();

    @Test
    void shouldMapToEntityCorrectlyTest() {
        assertNull(userMapper.toEntity((UserDto) null));
        assertEquals(NULL_USER, userMapper.toEntity(NULL_USER_DTO));
        assertEquals(NON_NULL_USER, userMapper.toEntity(NON_NULL_USER_DTO));
    }


    @Test
    void shouldMapToEntityDtoCorrectlyTest() {
        assertNull(userMapper.toEntityDto(null));
        assertEquals(NULL_USER_DTO, userMapper.toEntityDto(NULL_USER));
        assertEquals(NON_NULL_USER_DTO, userMapper.toEntityDto(NON_NULL_USER));
    }
}