package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.Builder;

@Builder
public record AddressDto(String street, String city, String state, String zoneId, String country) {

}
