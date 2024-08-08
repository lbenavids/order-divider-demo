package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.Address;
import com.cleveritgroup.orderdivider.core.domain.Stock;
import com.cleveritgroup.orderdivider.core.domain.Store;
import com.cleveritgroup.orderdivider.core.port.StoreRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.AddressDto;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.StockDto;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.StoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryAdapter implements StoreRepositoryPort {

    private final StoreRepository storeRepository;

    @Override
    public List<Store> findStoreWithSkus(Set<String> orderItemSkus) {
        return storeRepository.findStoreWithSkus(orderItemSkus).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<Store> stores) {
        storeRepository.saveAll(stores.stream().map(this::toDTO).collect(Collectors.toList()));
    }

    private StoreDto toDTO(Store store) {
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .address(toDTO(store.getAddress()))
                .stock(store.getStock().stream().map(this::toDTO).collect(Collectors.toList()))
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

    private StockDto toDTO(Stock stock) {
        return StockDto.builder()
                .sku(stock.getSku())
                .quantity(stock.getQuantity())
                .build();
    }

    private Store toDomain(StoreDto storeDto) {
        return Store.builder()
                .storeId(storeDto.getStoreId())
                .address(toDomain(storeDto.getAddress()))
                .stock(storeDto.getStock().stream().map(this::toDomain).collect(Collectors.toList()))
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

    private Stock toDomain(StockDto stockDto) {
        return Stock.builder()
                .sku(stockDto.getSku())
                .quantity(stockDto.getQuantity())
                .build();
    }
}