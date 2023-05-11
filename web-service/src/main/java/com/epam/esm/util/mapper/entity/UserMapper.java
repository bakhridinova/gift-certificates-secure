package com.epam.esm.util.mapper.entity;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.util.mapper.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {

}
