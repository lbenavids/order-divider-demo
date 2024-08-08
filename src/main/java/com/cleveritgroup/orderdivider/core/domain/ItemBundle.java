package com.cleveritgroup.orderdivider.core.domain;

public record ItemBundle(Item item, String storeId) {

    public String getSku() {
        return item.sku();
    }

    public int getQuantity() {
        return item.quantity();
    }

    public int getPrice() {
        return item.price();
    }

    public int totalAmount() {
        return item.totalAmount();
    }
}