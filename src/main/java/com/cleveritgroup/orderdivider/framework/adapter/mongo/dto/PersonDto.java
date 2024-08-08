package com.cleveritgroup.orderdivider.framework.adapter.mongo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
public record PersonDto(String firstName, String lastName, String email, String phoneNumber) {
}
