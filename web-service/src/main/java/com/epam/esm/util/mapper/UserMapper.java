package com.epam.esm.util.mapper;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {

}
