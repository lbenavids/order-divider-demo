package com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.*;

public class Mapper {

    public static OrderDto toDTO(Order order) {
        return OrderDto.builder()
                .orderId(order.orderId())
                .items(order.getItems().stream().map(Mapper::toDTO).toList())
                .deliveryAddress(toDTO(order.deliveryAddress()))
                .totalAmount(order.totalAmount())
                .buyer(toDTO(order.buyer()))
                .build();
    }

    public static ItemDto toDTO(Item item) {
        return ItemDto.builder()
                .sku(item.sku())
                .quantity(item.quantity())
                .price(item.price())
                .totalAmount(item.totalAmount())
                .build();
    }

    public static AddressDto toDTO(Address address) {
        return AddressDto.builder()
                .street(address.street())
                .city(address.city())
                .state(address.state())
                .zoneId(address.zoneId())
                .country(address.country())
                .build();
    }

    public static PersonDto toDTO(Person person) {
        return PersonDto.builder()
                .firstName(person.firstName())
                .lastName(person.lastName())
                .email(person.email())
                .phoneNumber(person.phoneNumber())
                .build();
    }

    public static Address toDomain(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.street())
                .city(addressDto.city())
                .state(addressDto.state())
                .zoneId(addressDto.zoneId())
                .country(addressDto.country())
                .build();
    }

    public static DeliveryCostDto toDTO(DeliveryCost deliveryCost) {
        return DeliveryCostDto.builder()
                .fromZoneId(deliveryCost.fromZoneId())
                .toZoneId(deliveryCost.toZoneId())
                .cost(deliveryCost.cost())
                .build();
    }

    public static DeliveryCost toDomain(DeliveryCostDto deliveryCostDto) {
        return DeliveryCost.builder()
                .fromZoneId(deliveryCostDto.fromZoneId())
                .toZoneId(deliveryCostDto.toZoneId())
                .cost(deliveryCostDto.cost())
                .build();
    }

    public static StoreDto toDTO(Store store) {
        return StoreDto.builder()
                .storeId(store.storeId())
                .address(toDTO(store.address()))
                .stock(store.stock().stream().map(Mapper::toDTO).toList())
                .build();
    }

    public static StockDto toDTO(Stock stock) {
        return StockDto.builder()
                .sku(stock.sku())
                .quantity(stock.quantity())
                .build();
    }

    public static Store toDomain(StoreDto storeDto) {
        return Store.builder()
                .storeId(storeDto.storeId())
                .address(toDomain(storeDto.address()))
                .stock(storeDto.stock().stream().map(Mapper::toDomain).toList())
                .build();
    }

    public static Stock toDomain(StockDto stockDto) {
        return Stock.builder()
                .sku(stockDto.sku())
                .quantity(stockDto.quantity())
                .build();
    }

    public static DeliveryBundleDto toDTO(DeliveryBundle bundle) {
        return DeliveryBundleDto.builder()
                .id(bundle.id())
                .orderId(bundle.orderId())
                .items(bundle.items().stream().map(Mapper::toDTO).toList())
                .deliveryAddress(toDTO(bundle.deliveryAddress()))
                .totalAmount(bundle.totalAmount())
                .state(bundle.state())
                .deliveryCost(bundle.deliveryCost())
                .buyer(toDTO(bundle.buyer()))
                .build();
    }

    public static ItemBundleDto toDTO(ItemBundle itemBundle) {
        return ItemBundleDto.builder()
                .sku(itemBundle.getSku())
                .quantity(itemBundle.getQuantity())
                .price(itemBundle.getPrice())
                .totalAmount(itemBundle.totalAmount())
                .storeId(itemBundle.storeId())
                .build();
    }

}