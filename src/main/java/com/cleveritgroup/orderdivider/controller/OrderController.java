package com.cleveritgroup.orderdivider.controller;

import com.cleveritgroup.orderdivider.entity.DeliveryBundle;
import com.cleveritgroup.orderdivider.entity.Order;
import com.cleveritgroup.orderdivider.service.OrderService;
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

    private final OrderService orderService;


    @PostMapping
    public List<DeliveryBundle> processOrder(@RequestBody Order order) {
        return orderService.splitOrder(order);
    }

}
