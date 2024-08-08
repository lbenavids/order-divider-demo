package com.cleveritgroup.orderdivider.core.domain;

import com.cleveritgroup.orderdivider.core.service.TestDataFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.cleveritgroup.orderdivider.core.service.TestDataFactory.AddressFactory.createAddress;
import static com.cleveritgroup.orderdivider.core.service.TestDataFactory.OrderFactory.getItem1;
import static com.cleveritgroup.orderdivider.core.service.TestDataFactory.OrderFactory.getItem2;
import static com.cleveritgroup.orderdivider.core.service.TestDataFactory.OrderFactory.getItem3;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DeliveryBundleTest {

    static Stream<Arguments> provideDeliveryBundles() {
        return Stream.of(
                Arguments.of(
                        List.of(
                                new ItemBundle(getItem1(), "Store1"),
                                new ItemBundle(getItem2(), "Store2")
                        ),
                        createAddress("12345"),
                        30,
                        110
                ),
                Arguments.of(
                        List.of(
                                new ItemBundle(getItem3(), "Store3")
                        ),
                        createAddress("67890"),
                        100,
                        130
                ),
                Arguments.of(
                        List.of(),
                        createAddress("11111"),
                        0,
                        0
                )
        );
    }

    @ParameterizedTest(name = "Given the items and delivery cost, the totalAmount should be {3}")
    @MethodSource("provideDeliveryBundles")
    void totalAmount_should_be_correct(List<ItemBundle> items, Address deliveryAddress, int deliveryCost, int expectedTotalAmount) {
        DeliveryBundle deliveryBundle = DeliveryBundle.builder()
                .id("dummy-id")
                .orderId("dummy-order-id")
                .items(items)
                .deliveryAddress(deliveryAddress)
                .state(DeliveryState.ASSIGNED_TO_STORE)
                .deliveryCost(deliveryCost)
                .buyer(TestDataFactory.PersonFactory.createPerson())
                .build();

        assertEquals(expectedTotalAmount, deliveryBundle.totalAmount());
    }
}