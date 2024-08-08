package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCostDto {
    private String fromZoneId;
    private String toZoneId;
    private Integer cost;
}
