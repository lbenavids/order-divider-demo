package com.cleveritgroup.orderdivider.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryBundle {
    private String id;
    private String orderId;
    private List<ItemBundle> items;
    private Address deliveryAddress;
    private Integer totalAmount;
    private DeliveryState state;
    private int deliveryCost;
    private Person buyer;
}
