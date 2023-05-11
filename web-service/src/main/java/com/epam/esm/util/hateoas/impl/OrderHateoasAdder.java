package com.epam.esm.util.hateoas.impl;

import com.epam.esm.controller.CertificateController;
import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDto;
import com.epam.esm.util.hateoas.HateoasAdder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderHateoasAdder implements HateoasAdder<OrderDto> {

    @Override
    public void addLinksToEntity(OrderDto order) {
        order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class)
                .getById(order.getId())).withSelfRel());
        order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class)
                .getById(order.getUserId())).withRel("GET order's user"));
        order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(CertificateController.class)
                .getById(order.getCertificateId())).withRel("GET order's certificate"));
    }
}
