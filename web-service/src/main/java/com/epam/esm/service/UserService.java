package com.epam.esm.service;


import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserDto;

/**
 * interface holding business logic for users
 *
 * @author bakhridinova
 */

public interface UserService extends BaseService<UserDto> {
    TokenDto singUp(UserDto user);
    TokenDto signIn(UserDto user);
}
