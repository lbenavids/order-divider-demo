package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

@Builder
public record Item(String sku, int quantity, int price) {

    public int totalAmount() {
        return quantity * price;
    }
}