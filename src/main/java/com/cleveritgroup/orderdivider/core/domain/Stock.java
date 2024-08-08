package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

@Builder
public record Stock(String sku, int quantity) {
}
