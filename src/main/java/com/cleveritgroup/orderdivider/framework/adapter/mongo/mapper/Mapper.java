package com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.*;

public class Mapper {

    public static OrderDto toDTO(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .items(order.getItems().stream().map(Mapper::toDTO).toList())
                .deliveryAddress(toDTO(order.getDeliveryAddress()))
                .totalAmount(order.getTotalAmount())
                .buyer(toDTO(order.getBuyer()))
                .build();
    }

    public static ItemDto toDTO(Item item) {
        return ItemDto.builder()
                .sku(item.getSku())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalAmount(item.getTotalAmount())
                .build();
    }

    public static AddressDto toDTO(Address address) {
        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zoneId(address.getZoneId())
                .country(address.getCountry())
                .build();
    }

    public static PersonDto toDTO(Person person) {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    public static Address toDomain(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zoneId(addressDto.getZoneId())
                .country(addressDto.getCountry())
                .build();
    }

    public static DeliveryCostDto toDTO(DeliveryCost deliveryCost) {
        return DeliveryCostDto.builder()
                .fromZoneId(deliveryCost.getFromZoneId())
                .toZoneId(deliveryCost.getToZoneId())
                .cost(deliveryCost.getCost())
                .build();
    }

    public static DeliveryCost toDomain(DeliveryCostDto deliveryCostDto) {
        return DeliveryCost.builder()
                .fromZoneId(deliveryCostDto.getFromZoneId())
                .toZoneId(deliveryCostDto.getToZoneId())
                .cost(deliveryCostDto.getCost())
                .build();
    }

    public static StoreDto toDTO(Store store) {
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .address(toDTO(store.getAddress()))
                .stock(store.getStock().stream().map(Mapper::toDTO).toList())
                .build();
    }

    public static StockDto toDTO(Stock stock) {
        return StockDto.builder()
                .sku(stock.getSku())
                .quantity(stock.getQuantity())
                .build();
    }

    public static Store toDomain(StoreDto storeDto) {
        return Store.builder()
                .storeId(storeDto.getStoreId())
                .address(toDomain(storeDto.getAddress()))
                .stock(storeDto.getStock().stream().map(Mapper::toDomain).toList())
                .build();
    }

    public static Stock toDomain(StockDto stockDto) {
        return Stock.builder()
                .sku(stockDto.getSku())
                .quantity(stockDto.getQuantity())
                .build();
    }

    public static DeliveryBundleDto toDTO(DeliveryBundle bundle) {
        return DeliveryBundleDto.builder()
                .id(bundle.getId())
                .orderId(bundle.getOrderId())
                .items(bundle.getItems().stream().map(Mapper::toDTO).toList())
                .deliveryAddress(toDTO(bundle.getDeliveryAddress()))
                .totalAmount(bundle.getTotalAmount())
                .state(bundle.getState())
                .deliveryCost(bundle.getDeliveryCost())
                .buyer(toDTO(bundle.getBuyer()))
                .build();
    }

    public static ItemBundleDto toDTO(ItemBundle itemBundle) {
        return ItemBundleDto.builder()
                .sku(itemBundle.getSku())
                .quantity(itemBundle.getQuantity())
                .price(itemBundle.getPrice())
                .totalAmount(itemBundle.getTotalAmount())
                .storeId(itemBundle.getStoreId())
                .build();
    }

}