package com.cleveritgroup.orderdivider.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class DeliveryCost {
    private String fromZoneId;
    private String toZoneId;
    private Integer cost;
}
