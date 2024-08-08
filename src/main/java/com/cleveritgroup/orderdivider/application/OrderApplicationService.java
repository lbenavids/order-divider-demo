package com.cleveritgroup.orderdivider.application;

import com.cleveritgroup.orderdivider.core.domain.DeliveryBundle;
import com.cleveritgroup.orderdivider.core.domain.Order;
import com.cleveritgroup.orderdivider.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderApplicationService {

    private final OrderService orderService;

    public List<DeliveryBundle> splitOrder(Order order) {
        return orderService.splitOrder(order);
    }
}