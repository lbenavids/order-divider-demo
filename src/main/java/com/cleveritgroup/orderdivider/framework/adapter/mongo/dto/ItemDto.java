package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;

@Builder
public record ItemDto(String sku, int quantity, int price, int totalAmount) {
}
