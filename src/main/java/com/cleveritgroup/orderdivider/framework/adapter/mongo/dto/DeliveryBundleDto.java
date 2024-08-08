package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import com.cleveritgroup.orderdivider.core.domain.DeliveryState;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
public record DeliveryBundleDto(
    @Id
    String id,
    String orderId,
    List<ItemBundleDto> items,
    AddressDto deliveryAddress,
    Integer totalAmount,
    DeliveryState state,
    int deliveryCost,
    PersonDto buyer) {
}
