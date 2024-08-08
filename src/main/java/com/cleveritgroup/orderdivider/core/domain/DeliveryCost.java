package com.cleveritgroup.orderdivider.core.domain;

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
public class DeliveryCost {
    private String fromZoneId;
    private String toZoneId;
    private Integer cost;
}
