package com.cleveritgroup.orderdivider.core.port;

import com.cleveritgroup.orderdivider.core.domain.Order;

public interface OrderRepositoryPort {
    void save(Order order);
}