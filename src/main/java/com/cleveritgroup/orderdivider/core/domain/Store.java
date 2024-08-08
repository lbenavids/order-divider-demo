package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

import java.util.List;

@Builder
public record Store(
        String storeId,
        Address address,
        List<Stock> stock) {
}
