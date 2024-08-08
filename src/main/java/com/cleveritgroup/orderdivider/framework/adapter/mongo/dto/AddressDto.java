package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private String street;
    private String city;
    private String state;
    private String zoneId;
    private String country;
}
