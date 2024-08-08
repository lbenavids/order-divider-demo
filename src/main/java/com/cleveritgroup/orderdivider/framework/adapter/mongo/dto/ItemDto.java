package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String sku;
    private int quantity;
    private int price;
    private int totalAmount;
}
