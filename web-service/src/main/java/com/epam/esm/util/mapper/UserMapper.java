package com.epam.esm.util.mapper;

import com.epam.esm.dto.UserCredentialsDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    User toEntity(UserCredentialsDto userCredentials);
}
