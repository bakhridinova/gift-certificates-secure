package com.epam.esm.util.hateoas.impl;

import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.UserDto;
import com.epam.esm.util.hateoas.HateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserHateoasAdder implements HateoasAdder<UserDto> {

    @Override
    public void addLinksToEntity(UserDto user) {
        user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getById(user.getId())).withSelfRel());
        user.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                .getByUserId(user.getId(), 0, 5)).withRel("GET user's orders"));
    }
}
