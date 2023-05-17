package com.epam.esm.util.hateoas.impl;

import com.epam.esm.controller.TokenController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.TokenDto;
import com.epam.esm.util.hateoas.HateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TokenHateoasAdder implements HateoasAdder<TokenDto> {
    @Override
    public void addLinksToEntity(TokenDto token) {
        token.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TokenController.class)
                .getById(token.getId())).withSelfRel());
        token.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getById(token.getUserId())).withRel("GET token's user"));
    }
}
