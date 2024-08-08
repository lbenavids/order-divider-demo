package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record Order(String orderId, List<Item> items, Address deliveryAddress, Person buyer) {

    public int totalAmount() {
        return items.stream().mapToInt(Item::totalAmount).sum();
    }

    public List<Item> getItems() {
        return List.copyOf(items);
    }

}