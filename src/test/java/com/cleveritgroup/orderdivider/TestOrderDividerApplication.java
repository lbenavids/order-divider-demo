package com.cleveritgroup.orderdivider;

import org.springframework.boot.SpringApplication;

public class TestOrderDividerApplication {

    public static void main(String[] args) {
        SpringApplication.from(OrderDividerApplication::main)
                .with(TestcontainersConfiguration.class)
                .run(args);
    }

}
