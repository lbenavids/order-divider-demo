package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import com.cleveritgroup.orderdivider.core.domain.DeliveryState;
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
    private DeliveryState state;
    private int deliveryCost;
    private PersonDto buyer;
}
