package com.cleveritgroup.orderdivider.core.service;

import com.cleveritgroup.orderdivider.core.domain.Address;
import com.cleveritgroup.orderdivider.core.domain.DeliveryCost;
import com.cleveritgroup.orderdivider.core.domain.Item;
import com.cleveritgroup.orderdivider.core.domain.Order;
import com.cleveritgroup.orderdivider.core.domain.Person;
import com.cleveritgroup.orderdivider.core.domain.Stock;
import com.cleveritgroup.orderdivider.core.domain.Store;

import java.util.List;

public class TestDataFactory {

    public static class OrderFactory {
        private static final Item ITEM_1 = Item.builder()
                .sku("1")
                .price(10)
                .quantity(2)
                .build();

        private static final Item ITEM_2 = Item.builder()
                .sku("2")
                .price(20)
                .quantity(3)
                .build();

        private static final Item ITEM_3 = Item.builder()
                .sku("3")
                .price(30)
                .quantity(1)
                .build();

        public static Order createOrderWithItems(List<Item> items) {
            return Order.builder()
                    .orderId("1")
                    .deliveryAddress(AddressFactory.createAddress("12345"))
                    .buyer(PersonFactory.createPerson())
                    .items(items)
                    .build();
        }

        public static Item getItem1() {
            return ITEM_1;
        }

        public static Item getItem2() {
            return ITEM_2;
        }

        public static Item getItem3() {
            return ITEM_3;
        }
    }

    public static class AddressFactory {
        public static Address createAddress(String zoneId) {
            return Address.builder()
                    .city("Santiago")
                    .country("Chile")
                    .street("Av. Providencia 1760")
                    .zoneId(zoneId)
                    .build();
        }
    }

    public static class PersonFactory {
        public static Person createPerson() {
            return Person.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("dummy@dummy.com")
                    .build();
        }
    }

    public static class StoreFactory {
        public static Store createStore1() {
            return Store.builder()
                    .storeId("Store1")
                    .address(AddressFactory.createAddress("11111"))
                    .stock(List.of(
                            Stock.builder().sku("1").quantity(10).build(),
                            Stock.builder().sku("2").quantity(10).build(),
                            Stock.builder().sku("3").quantity(10).build()
                    ))
                    .build();
        }

        public static Store createStore2() {
            return Store.builder()
                    .storeId("Store2")
                    .address(AddressFactory.createAddress("22222"))
                    .stock(List.of(
                            Stock.builder().sku("1").quantity(5).build()
                    ))
                    .build();
        }

        public static Store createStore3() {
            return Store.builder()
                    .storeId("Store3")
                    .address(AddressFactory.createAddress("33333"))
                    .stock(List.of(
                            Stock.builder().sku("2").quantity(5).build()
                    ))
                    .build();
        }

        public static Store createStore4() {
            return Store.builder()
                    .storeId("Store4")
                    .address(AddressFactory.createAddress("44444"))
                    .stock(List.of(
                            Stock.builder().sku("3").quantity(5).build()
                    ))
                    .build();
        }

        public static Store createStore5() {
            return Store.builder()
                    .storeId("Store5")
                    .address(AddressFactory.createAddress("55555"))
                    .stock(List.of())
                    .build();
        }

        public static Store createStore6() {
            return Store.builder()
                    .storeId("Store6")
                    .address(AddressFactory.createAddress("66666"))
                    .stock(List.of(
                            Stock.builder().sku("1").quantity(7).build(),
                            Stock.builder().sku("2").quantity(8).build()
                    ))
                    .build();
        }

        public static Store createStore7() {
            return Store.builder()
                    .storeId("Store7")
                    .address(AddressFactory.createAddress("77777"))
                    .stock(List.of(
                            Stock.builder().sku("1").quantity(6).build(),
                            Stock.builder().sku("3").quantity(4).build()
                    ))
                    .build();
        }

        public static Store createStore8() {
            return Store.builder()
                    .storeId("Store8")
                    .address(AddressFactory.createAddress("88888"))
                    .stock(List.of(
                            Stock.builder().sku("2").quantity(9).build(),
                            Stock.builder().sku("3").quantity(3).build()
                    ))
                    .build();
        }
    }

    public static class DeliveryCostFactory {
        public static List<DeliveryCost> createDeliveryCosts() {
            List<Store> stores = List.of(
                    StoreFactory.createStore1(), StoreFactory.createStore2(), StoreFactory.createStore3(),
                    StoreFactory.createStore4(), StoreFactory.createStore5(), StoreFactory.createStore6(),
                    StoreFactory.createStore7(), StoreFactory.createStore8()
            );
            String orderZoneId = OrderFactory.createOrderWithItems(List.of())
                    .deliveryAddress()
                    .zoneId();
            return stores.stream()
                    .map(store -> DeliveryCost.builder()
                            .fromZoneId(store.address().zoneId())
                            .toZoneId(orderZoneId)
                            .cost(Integer.parseInt(store.address().zoneId())) // Set cost as the integer value of the zoneId
                            .build())
                    .toList();
        }
    }
}