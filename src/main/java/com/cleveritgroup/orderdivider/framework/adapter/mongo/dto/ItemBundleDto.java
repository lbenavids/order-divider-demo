package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;

@Builder
public record ItemBundleDto(String storeId, String sku, int quantity, int price, int totalAmount) {
}
