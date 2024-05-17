package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.TokenDto;
import com.epam.esm.facade.TokenFacade;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tokens")
public class TokenController {
    private final TokenFacade tokenFacade;

    /**
     * GET endpoint to retrieve page of tokens
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of users
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<TokenDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size) {
        return tokenFacade.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific token by its ID
     *
     * @param id of required user
     * @return specified user
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public CustomResponse<TokenDto> getById(@PathVariable Long id) {
        return new CustomResponse<>(tokenFacade.findById(id));
    }

    /**
     * GET endpoint to search for retrieving tokens associated with specific user
     *
     * @param userId ID of user for which to retrieve tokens
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of tokens associated with certificate
     */
    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<TokenDto> getByUserId(@RequestParam(required = false) Long userId,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size) {
        return tokenFacade.findByUserIdAndPage(userId, page, size);
    }
}
