package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.TokenDto;
import com.epam.esm.dto.UserCredentialsDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserFacade userFacade;

    /**
     * GET endpoint to retrieve page of users
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<UserDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return userFacade.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific user by its ID
     *
     * @param id of required user
     * @return specified user
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public CustomResponse<UserDto> getById(@PathVariable Long id) {
        return new CustomResponse<>(userFacade.findById(id));
    }

    /**
     * POST endpoint to sign user up
     *
     * @param userCredentials with username and password
     * @return access token
     */
    @PostMapping("/register")
    public CustomResponse<TokenDto> signUp(@RequestBody UserCredentialsDto userCredentials) {
        return new CustomResponse<>(userFacade.singUp(userCredentials));
    }

    /**
     * POST endpoint to sign user in
     *
     * @param userCredentials with username and password
     * @return access token
     */
    @PostMapping("/authenticate")
    public CustomResponse<TokenDto> singIn(@RequestBody UserCredentialsDto userCredentials) {
        return new CustomResponse<>(userFacade.signIn(userCredentials));
    }
}
