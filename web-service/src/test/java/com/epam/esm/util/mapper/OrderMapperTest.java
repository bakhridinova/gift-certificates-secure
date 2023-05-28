package com.epam.esm.util.mapper;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderMapperTest {
    private final OrderMapper orderMapper;

    public OrderMapperTest() {
        orderMapper = new OrderMapperImpl();
    }

    private static final Order NULL_ORDER =
            Order.builder().build();
    private static final OrderDto NULL_ORDER_DTO =
            OrderDto.builder().build();

    private static final Order NON_NULL_ORDER =
            Order.builder().id(0L).build();
    private static final OrderDto NON_NULL_ORDER_DTO =
           OrderDto.builder().id(0L).build();

    @Test
    void shouldMapToEntityCorrectlyTest() {
        assertNull(orderMapper.toEntity(null));
        assertEquals(NULL_ORDER, orderMapper.toEntity(NULL_ORDER_DTO));
        assertEquals(NON_NULL_ORDER, orderMapper.toEntity(NON_NULL_ORDER_DTO));
    }


    @Test
    void shouldMapToEntityDtoCorrectlyTest() {
        assertNull(orderMapper.toEntityDto(null));
        assertEquals(NULL_ORDER_DTO, orderMapper.toEntityDto(NULL_ORDER));
        assertEquals(NON_NULL_ORDER_DTO, orderMapper.toEntityDto(NON_NULL_ORDER));
    }
}