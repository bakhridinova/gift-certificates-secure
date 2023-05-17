package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.UserDto;
import com.epam.esm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    /**
     * GET endpoint to retrieve list of users
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return List of users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<UserDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return userService.findAllByPage(page, size);
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
        return new CustomResponse<>(userService.findById(id));
    }

    @PostMapping("/register")
    public CustomResponse<?> signUp(@RequestBody UserDto user) {
        return new CustomResponse<>(HttpStatus.OK, userService.singUp(user));
    }

    @PostMapping("/authenticate")
    public CustomResponse<?> singIn(@RequestBody UserDto user) {
        return new CustomResponse<>(HttpStatus.OK, userService.signIn(user));
    }
}
