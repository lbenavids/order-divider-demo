package com.cleveritgroup.orderdivider.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Builder
public record Person(String firstName, String lastName, String email, String phoneNumber) {
}
