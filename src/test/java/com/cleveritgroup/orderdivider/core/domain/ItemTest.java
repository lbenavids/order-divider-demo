package com.cleveritgroup.orderdivider.core.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {

    @Test
    @DisplayName("Test constructor and getters")
    void testConstructorAndGetters() {
        Item item = new Item("12345", 10, 100);

        assertEquals("12345", item.sku());
        assertEquals(10, item.quantity());
        assertEquals(100, item.price());
    }

    @ParameterizedTest(name = "Given the quantity {1} and the price {2}, the totalAmount should be {3}")
    @CsvSource({
            "10, 100, 1000",
            "5, 200, 1000",
            "3, 333, 999",
            "0, 100, 0",
            "10, 0, 0",
            "7, 150, 1050",
            "1, 1000, 1000",
            "2, 500, 1000",
            "4, 250, 1000",
            "6, 166, 996"
    })
    void testTotalAmount(int quantity, int price, int expectedTotalAmount) {
        Item item = Item.builder()
                .sku("dummy-sku")
                .quantity(quantity)
                .price(price)
                .build();

        assertEquals(expectedTotalAmount, item.totalAmount());
    }
}