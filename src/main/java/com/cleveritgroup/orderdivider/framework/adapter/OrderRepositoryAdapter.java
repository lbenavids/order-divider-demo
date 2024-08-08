package com.cleveritgroup.orderdivider.framework.adapter;

import com.cleveritgroup.orderdivider.core.domain.Order;
import com.cleveritgroup.orderdivider.core.port.OrderRepositoryPort;
import com.cleveritgroup.orderdivider.framework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }
}