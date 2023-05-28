package com.epam.esm.util.mapper;

import com.epam.esm.dto.OrderDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {
        User.class,
        Certificate.class
})
public interface OrderMapper extends BaseMapper<Order, OrderDto> {
    @Override
    @Mapping(target = "userId", expression = "java(order.getUser() == null ? null : order.getUser().getId())")
    @Mapping(target = "certificateId", expression = "java(order.getCertificate() == null ? null : order.getCertificate().getId())")
    OrderDto toEntityDto(Order order);

    @Override
    @Mapping(target = "user", expression = "java(User.builder().id(order.getUserId()).build())")
    @Mapping(target = "certificate", expression = "java(Certificate.builder().id(order.getCertificateId()).build())")
    Order toEntity(OrderDto order);
}
