package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.core.port.OrderRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.OrderDto;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        OrderDto orderDTO = Mapper.toDTO(order);
        orderRepository.save(orderDTO);
    }
}