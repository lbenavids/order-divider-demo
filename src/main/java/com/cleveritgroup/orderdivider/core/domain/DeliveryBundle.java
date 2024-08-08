package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record DeliveryBundle(String id, String orderId, List<ItemBundle> items, Address deliveryAddress, DeliveryState state, int deliveryCost, Person buyer) {

    public int getTotalAmount() {
        return items.stream().mapToInt(ItemBundle::totalAmount).sum();
    }

    public List<ItemBundle> getItems() {
        return List.copyOf(items);
    }
}