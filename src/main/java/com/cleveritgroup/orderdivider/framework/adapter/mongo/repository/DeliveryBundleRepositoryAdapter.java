package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.core.port.DeliveryBundleRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DeliveryBundleRepositoryAdapter implements DeliveryBundleRepositoryPort {

    private final DeliveryBundleRepository deliveryBundleRepository;

    @Override
    public void saveAll(List<DeliveryBundle> bundles) {
        deliveryBundleRepository.saveAll(bundles.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private DeliveryBundleDto toDTO(DeliveryBundle bundle) {
        return DeliveryBundleDto.builder()
                .id(bundle.getId())
                .orderId(bundle.getOrderId())
                .items(bundle.getItems().stream().map(this::toDTO).collect(Collectors.toList()))
                .deliveryAddress(toDTO(bundle.getDeliveryAddress()))
                .totalAmount(bundle.getTotalAmount())
                .state(DeliveryStateDto.valueOf(bundle.getState().name()))
                .deliveryCost(bundle.getDeliveryCost())
                .buyer(toDTO(bundle.getBuyer()))
                .build();
    }

    private ItemBundleDto toDTO(ItemBundle itemBundle) {
        return ItemBundleDto.builder()
                .sku(itemBundle.getSku())
                .quantity(itemBundle.getQuantity())
                .price(itemBundle.getPrice())
                .totalAmount(itemBundle.getTotalAmount())
                .storeId(itemBundle.getStoreId())
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

    private DeliveryBundle toDomain(DeliveryBundleDto bundleDto) {
        return DeliveryBundle.builder()
                .id(bundleDto.getId())
                .orderId(bundleDto.getOrderId())
                .items(bundleDto.getItems().stream().map(this::toDomain).collect(Collectors.toList()))
                .deliveryAddress(toDomain(bundleDto.getDeliveryAddress()))
                .totalAmount(bundleDto.getTotalAmount())
                .state(DeliveryState.valueOf(bundleDto.getState().name()))
                .deliveryCost(bundleDto.getDeliveryCost())
                .buyer(toDomain(bundleDto.getBuyer()))
                .build();
    }

    private ItemBundle toDomain(ItemBundleDto itemBundleDto) {
        return new ItemBundle(
                Item.builder()
                        .sku(itemBundleDto.getSku())
                        .quantity(itemBundleDto.getQuantity())
                        .price(itemBundleDto.getPrice())
                        .totalAmount(itemBundleDto.getTotalAmount())
                        .build(),
                itemBundleDto.getStoreId()
        );
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