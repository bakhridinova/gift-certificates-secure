package com.epam.esm.facade.impl;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.facade.OrderFacade;
import com.epam.esm.service.OrderService;
import com.epam.esm.util.hateoas.OrderHateoasAdder;
import com.epam.esm.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderFacadeImpl implements OrderFacade {
    private final OrderHateoasAdder orderHateoasAdder;
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Override
    public Page<OrderDto> findAllByPage(int page, int size) {
        Page<OrderDto> orders = orderMapper.mapEntitiyPageToEntityDtoPage(
                orderService.findAllByPage(page, size), orderMapper);

        orderHateoasAdder.addLinksToEntityPage(orders);
        return orders;
    }

    @Override
    public OrderDto findById(Long id) {
        OrderDto order = orderMapper.toEntityDto(orderService.findById(id));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }

    @Override
    public Page<OrderDto> findByUserIdAndPage(Long userId, int page, int size) {
        Page<OrderDto> orders = orderMapper.mapEntitiyPageToEntityDtoPage(
                orderService.findByUserIdAndPage(userId, page, size), orderMapper);

        orderHateoasAdder.addLinksToEntityPage(orders);
        return orders;
    }

    @Override
    public OrderDto create(OrderDto order) {
        order = orderMapper.toEntityDto(orderService
                .create(orderMapper.toEntity(order)));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }

    @Override
    public OrderDto payById(Long id) {
        OrderDto order = orderMapper.toEntityDto(orderService.payById(id));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }

    @Override
    public OrderDto cancelById(Long id) {
        OrderDto order = orderMapper.toEntityDto(orderService.cancelById(id));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }
}
