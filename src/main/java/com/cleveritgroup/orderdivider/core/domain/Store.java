package com.cleveritgroup.orderdivider.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    private String storeId;
    private Address address;
    private List<Stock> stock;
}
