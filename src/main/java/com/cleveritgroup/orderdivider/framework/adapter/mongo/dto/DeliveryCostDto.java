package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public record DeliveryCostDto(String fromZoneId, String toZoneId, Integer cost) {
}
