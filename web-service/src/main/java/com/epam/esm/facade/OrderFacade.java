package com.epam.esm.facade;

import com.epam.esm.dto.OrderDto;
import org.springframework.data.domain.Page;

public interface OrderFacade extends BaseFacade<OrderDto> {
    Page<OrderDto> findByUserIdAndPage(Long userId, int page, int size);
    OrderDto create(OrderDto order);
    OrderDto payById(Long id);
    OrderDto cancelById(Long id);
}
