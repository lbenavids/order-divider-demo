package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@Data
public class DeliveryBundle {
    @Id
    private String id;
    private String orderId;
    private List<ItemBundle> items;
    private Address deliveryAddress;
    private Integer totalAmount;
    private DeliveryState state;
    private int deliveryCost;
    private Person buyer;
}
