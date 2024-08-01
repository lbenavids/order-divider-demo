package com.cleveritgroup.orderdivider.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
