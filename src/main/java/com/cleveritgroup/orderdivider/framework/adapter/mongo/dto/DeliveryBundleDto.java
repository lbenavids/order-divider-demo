package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@Data
public class DeliveryBundleDto {
    @Id
    private String id;
    private String orderId;
    private List<ItemBundleDto> items;
    private AddressDto deliveryAddress;
    private Integer totalAmount;
    private DeliveryStateDto state;
    private int deliveryCost;
    private PersonDto buyer;
}
