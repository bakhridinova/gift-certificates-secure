package com.epam.esm.service;


import com.epam.esm.entity.Token;
import com.epam.esm.entity.User;

/**
 * Interface holding business logic for users
 *
 * @author bakhridinova
 */

public interface UserService extends BaseService<User> {
    Token singUp(User user);
    Token signIn(User user);
}
