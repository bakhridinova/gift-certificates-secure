package com.epam.esm.service;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.impl.OrderServiceImpl;
import com.epam.esm.util.hateoas.BaseHateoasAdder;
import com.epam.esm.util.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;

    @Mock
    private BaseHateoasAdder<OrderDto> orderHateoasAdder;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void contextLoads() {
        assertNotNull(orderService);
    }
}