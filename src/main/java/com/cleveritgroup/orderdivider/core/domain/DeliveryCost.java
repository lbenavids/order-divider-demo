package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

@Builder
public record DeliveryCost(String fromZoneId, String toZoneId, Integer cost) {
}
