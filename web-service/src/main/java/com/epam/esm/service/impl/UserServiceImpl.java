package com.epam.esm.service.impl;

import com.epam.esm.entity.Token;
import com.epam.esm.entity.User;
import com.epam.esm.enums.TokenType;
import com.epam.esm.enums.UserRole;
import com.epam.esm.exception.AuthenticationFailedException;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.security.authentication.basic.CustomBasicAuthentication;
import com.epam.esm.security.authentication.service.CustomUserDetails;
import com.epam.esm.security.authentication.service.JwtService;
import com.epam.esm.service.UserService;
import com.epam.esm.util.enums.field.UserField;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomUserValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public Page<User> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(Long id) {
        CustomValidator.validateId(UserField.ID, id);

        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }

    @Override
    @Transactional
    public Token singUp(User user) {
        CustomUserValidator.validate(user);
        final String password = user.getPassword();

        userRepository.findByUsername(user.getUsername()).ifPresent((u) -> {
            throw new EntityAlreadyExistsException("username already taken");
        });

        user = userRepository.save(
                User.builder().role(UserRole.USER).username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword())).build()
        );

        return getTokenForUser(user, password);
    }

    @Override
    public Token signIn(User user) {
        final String password = user.getPassword();
        user = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("username does not exist"));

        return getTokenForUser(user, password);
    }

    private Token getTokenForUser(User user, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new CustomBasicAuthentication(false,
                        user.getUsername(), password));

        if (!authentication.isAuthenticated()) {
            throw new AuthenticationFailedException(user.getUsername());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = new CustomUserDetails(user);
        return tokenRepository.save(Token.builder()
                .accessToken(jwtService.generateToken(userDetails))
                .type(TokenType.BEARER).user(user).build());
    }
}
