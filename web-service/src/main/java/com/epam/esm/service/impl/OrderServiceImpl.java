package com.epam.esm.service.impl;

import com.epam.esm.entity.Order;
import com.epam.esm.enums.OrderStatus;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.service.OrderService;
import com.epam.esm.util.enums.field.CertificateField;
import com.epam.esm.util.enums.field.OrderField;
import com.epam.esm.util.enums.field.UserField;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public Page<Order> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        return orderRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Order findById(Long id) {
        CustomValidator.validateId(OrderField.ID, id);

        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Order.class, id));
    }

    @Override
    public Page<Order> findByUserIdAndPage(Long userId, int page, int size) {
        CustomPageValidator.validate(page, size);
        CustomValidator.validateId(UserField.ID, userId);

        return orderRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public Order create(Order order) {
        CustomValidator.validateId(UserField.ID, order.getUser().getId());
        CustomValidator.validateId(CertificateField.ID, order.getCertificate().getId());

        return orderRepository.create(order.getUser().getId(), order.getCertificate().getId());
    }

    @Override
    @Transactional
    public Order payById(Long id) {
        CustomValidator.validateId(OrderField.ID, id);

        return orderRepository.updateStatusById(id, OrderStatus.PAID.toString());
    }

    @Override
    @Transactional
    public Order cancelById(Long id) {
        CustomValidator.validateId(OrderField.ID, id);

        return orderRepository.updateStatusById(id, OrderStatus.CANCELLED.toString());
    }
}
