package com.cleveritgroup.orderdivider.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Store {
    @Id
    private String storeId;
    private Address address;
    private List<Stock> stock;
}
