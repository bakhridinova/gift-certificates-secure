package com.epam.esm.service;

import com.epam.esm.entity.Order;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.impl.OrderServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private static final Class<Order> TYPE_CLASS = Order.class;

    @Test
    void contextLoads() {
        assertNotNull(orderService);
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsNegative() {
        assertEquals("page number should not be negative",
                assertThrows(ValidationException.class,
                        () -> orderService.findAllByPage(Integer.MIN_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsNegative() {
        assertEquals("page size should not be negative",
                assertThrows(ValidationException.class,
                        () -> orderService.findAllByPage(0, Integer.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsTooHigh() {
        assertEquals("page number must be between 0 and 10000",
                assertThrows(ValidationException.class,
                        () -> orderService.findAllByPage(Integer.MAX_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsTooHigh() {
        assertEquals("page size must be between 0 and 100",
                assertThrows(ValidationException.class,
                        () -> orderService.findAllByPage(0, Integer.MAX_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("order id should not be null",
                assertThrows(ValidationException.class,
                        () -> orderService.findById(null))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("order id must be positive",
                assertThrows(ValidationException.class,
                        () -> orderService.findById(0L))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("order id must be positive",
                assertThrows(ValidationException.class,
                        () -> orderService.findById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowNotFoundException_IfOptionalIsEmpty() {
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        String message = String.format("failed to find %s by id %d",
                TYPE_CLASS.getSimpleName(), 1L);

        assertEquals(message,
                assertThrows(EntityNotFoundException.class,
                        () -> orderService.findById(1L))
                        .getMessage());
    }

    @Test
    void findById_ShouldNotThrowException_IfOptionalIsPresent() {
        when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> orderService.findById(1L));
    }
}