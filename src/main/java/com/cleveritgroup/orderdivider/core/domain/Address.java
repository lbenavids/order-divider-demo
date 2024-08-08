package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

@Builder
public record Address(String street, String city, String state, String zoneId, String country) {

}
