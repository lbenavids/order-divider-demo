package com.cleveritgroup.orderdivider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    protected String sku;
    protected int quantity;
    protected int price;
    protected int totalAmount;
}
