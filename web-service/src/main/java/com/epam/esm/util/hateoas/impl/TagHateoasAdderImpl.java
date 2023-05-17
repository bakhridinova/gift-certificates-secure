package com.epam.esm.util.hateoas.impl;

import com.epam.esm.controller.TagController;
import com.epam.esm.dto.TagDto;
import com.epam.esm.util.hateoas.TagHateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class TagHateoasAdderImpl implements TagHateoasAdder {

    @Override
    public void addLinksToEntity(TagDto tag) {
        tag.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(TagController.class)
                .getById(tag.getId())).withSelfRel());
    }
}
