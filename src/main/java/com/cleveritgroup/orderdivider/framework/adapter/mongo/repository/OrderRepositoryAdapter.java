package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.core.port.OrderRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        OrderDto orderDTO = toDTO(order);
        orderRepository.save(orderDTO);
    }

    private OrderDto toDTO(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .items(order.getItems().stream().map(this::toDTO).toList())
                .deliveryAddress(toDTO(order.getDeliveryAddress()))
                .totalAmount(order.getTotalAmount())
                .buyer(toDTO(order.getBuyer()))
                .build();
    }

    private ItemDto toDTO(Item item) {
        return ItemDto.builder()
                .sku(item.getSku())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .totalAmount(item.getTotalAmount())
                .build();
    }

    private AddressDto toDTO(Address address) {
        return AddressDto.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zoneId(address.getZoneId())
                .country(address.getCountry())
                .build();
    }

    private PersonDto toDTO(Person person) {
        return PersonDto.builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .phoneNumber(person.getPhoneNumber())
                .build();
    }

    private Order toDomain(OrderDto orderDto) {
        return Order.builder()
                .orderId(orderDto.getOrderId())
                .items(orderDto.getItems().stream().map(this::toDomain).toList())
                .deliveryAddress(toDomain(orderDto.getDeliveryAddress()))
                .totalAmount(orderDto.getTotalAmount())
                .buyer(toDomain(orderDto.getBuyer()))
                .build();
    }

    private Item toDomain(ItemDto itemDto) {
        return Item.builder()
                .sku(itemDto.getSku())
                .quantity(itemDto.getQuantity())
                .price(itemDto.getPrice())
                .totalAmount(itemDto.getTotalAmount())
                .build();
    }

    private Address toDomain(AddressDto addressDto) {
        return Address.builder()
                .street(addressDto.getStreet())
                .city(addressDto.getCity())
                .state(addressDto.getState())
                .zoneId(addressDto.getZoneId())
                .country(addressDto.getCountry())
                .build();
    }

    private Person toDomain(PersonDto personDto) {
        return Person.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .email(personDto.getEmail())
                .phoneNumber(personDto.getPhoneNumber())
                .build();
    }
}