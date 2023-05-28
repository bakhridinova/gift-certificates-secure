package com.epam.esm.service;


import com.epam.esm.entity.Order;
import org.springframework.data.domain.Page;

/**
 * Interface holding business logic for orders
 *
 * @author bakhridinova
 */

public interface OrderService extends BaseService<Order> {
    Page<Order> findByUserIdAndPage(Long userId, int page, int size);
    Order payById(Long id);
    Order cancelById(Long id);
}
