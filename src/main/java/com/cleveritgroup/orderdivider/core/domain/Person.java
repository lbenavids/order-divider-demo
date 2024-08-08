package com.cleveritgroup.orderdivider.core.domain;

import lombok.Builder;

@Builder
public record Person(String firstName, String lastName, String email, String phoneNumber) {
}
