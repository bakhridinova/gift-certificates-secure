package com.epam.esm.util.mapper.entity;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Order;
import com.epam.esm.util.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    @Override
    @Mapping(target = "userId", expression = "java(order.getUser().getId())")
    @Mapping(target = "certificateId", expression = "java(order.getCertificate().getId())")
    OrderDto toEntityDto(Order order);
}
