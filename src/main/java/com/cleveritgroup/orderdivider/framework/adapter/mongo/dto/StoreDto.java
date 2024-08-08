package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
public record StoreDto(@Id String storeId, AddressDto address, List<StockDto> stock) {
}
