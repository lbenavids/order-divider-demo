package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
public record StockDto(String sku, int quantity) {
}
