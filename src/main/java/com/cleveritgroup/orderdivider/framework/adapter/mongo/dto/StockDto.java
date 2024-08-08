package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;

@Builder
public record StockDto(String sku, int quantity) {
}
