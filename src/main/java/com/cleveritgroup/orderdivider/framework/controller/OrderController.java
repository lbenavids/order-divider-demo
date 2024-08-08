package com.cleveritgroup.orderdivider.framework.controller;

import com.cleveritgroup.orderdivider.core.domain.DeliveryBundle;
import com.cleveritgroup.orderdivider.core.domain.Order;
import com.cleveritgroup.orderdivider.application.OrderApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderApplicationService orderApplicationService;

    @PostMapping
    public List<DeliveryBundle> processOrder(@RequestBody Order order) {
        return orderApplicationService.splitOrder(order);
    }
}