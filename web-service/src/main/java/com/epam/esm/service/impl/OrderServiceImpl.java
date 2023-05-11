package com.epam.esm.service.impl;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.OrderService;
import com.epam.esm.util.hateoas.HateoasAdder;
import com.epam.esm.util.mapper.entity.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final HateoasAdder<OrderDto> orderHateoasAdder;

    @Override
    public Page<OrderDto> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDto> orders = orderMapper.mapEntitiyPageToEntityDtoPage(orderRepository
                .findAll(pageable), orderMapper);
        orderHateoasAdder.addLinksToEntityPage(orders);

        return orders;
    }

    @Override
    public OrderDto findById(Long id) {
        OrderDto order = orderMapper.toEntityDto(orderRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(
                        "failed to find order by orderId " + id)));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }

    @Override
    public Page<OrderDto> findByUserIdAndPage(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OrderDto> orders = orderMapper.mapEntitiyPageToEntityDtoPage(orderRepository
                .findByUserId(userId, pageable), orderMapper);

        orderHateoasAdder.addLinksToEntityPage(orders);
        return orders;
    }

    @Override
    @Transactional
    public OrderDto create(OrderDto order) {
        order = orderMapper.toEntityDto(orderRepository
                .create(order.getCertificateId(), order.getUserId()));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }

    @Override
    @Transactional
    public OrderDto payById(Long id) {
        OrderDto orderDto = orderMapper.toEntityDto(orderRepository
                .updateStatusById(id, Order.Status.PAID.toString()));

        orderHateoasAdder.addLinksToEntity(orderDto);
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto cancelById(Long id) {
        OrderDto order = orderMapper.toEntityDto(orderRepository
                .updateStatusById(id, Order.Status.CANCELLED.toString()));

        orderHateoasAdder.addLinksToEntity(order);
        return order;
    }
}
