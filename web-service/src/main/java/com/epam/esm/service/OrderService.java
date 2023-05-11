package com.epam.esm.service;


import com.epam.esm.dto.OrderDto;
import org.springframework.data.domain.Page;

/**
 * interface holding business logic for orders
 *
 * @author bakhridinova
 */

public interface OrderService extends BaseService<OrderDto> {
    Page<OrderDto> findByUserIdAndPage(Long userId, int page, int size);
    OrderDto payById(Long id);
    OrderDto cancelById(Long id);
}
