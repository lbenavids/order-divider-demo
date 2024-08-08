package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    @Id
    private String orderId;
    private List<ItemDto> items;
    private AddressDto deliveryAddress;
    private int totalAmount;
    private PersonDto buyer;
}
