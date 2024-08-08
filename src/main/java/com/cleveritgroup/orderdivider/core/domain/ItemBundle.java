package com.cleveritgroup.orderdivider.core.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ItemBundle extends Item {
    private String storeId;

    public ItemBundle(Item item, String storeId) {
        this.storeId= storeId;
        this.price= item.getPrice();
        this.quantity= item.getQuantity();
        this.sku= item.getSku();
        this.totalAmount= item.getTotalAmount();
    }



}
