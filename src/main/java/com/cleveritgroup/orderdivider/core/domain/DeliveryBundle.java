package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record DeliveryBundle(String id, String orderId, List<ItemBundle> items, Address deliveryAddress, DeliveryState state, int deliveryCost, Person buyer) {

    public DeliveryBundle {
        items = List.copyOf(items);
    }

    public int totalAmount() {
        return items.stream().mapToInt(ItemBundle::totalAmount).sum() + deliveryCost;
    }
}