package com.epam.esm.facade;

import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserCredentialsDto;
import com.epam.esm.dto.UserDto;

public interface UserFacade extends BaseFacade<UserDto> {
    TokenDto singUp(UserCredentialsDto user);
    TokenDto signIn(UserCredentialsDto user);
}
