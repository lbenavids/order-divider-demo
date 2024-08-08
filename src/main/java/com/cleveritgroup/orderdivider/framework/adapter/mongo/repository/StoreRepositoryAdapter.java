package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.Store;
import com.cleveritgroup.orderdivider.core.port.StoreRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class StoreRepositoryAdapter implements StoreRepositoryPort {

    private final StoreRepository storeRepository;

    @Override
    public List<Store> findStoreWithSkus(Set<String> orderItemSkus) {
        return storeRepository.findStoreWithSkus(orderItemSkus).stream().map(Mapper::toDomain).toList();
    }

    @Override
    public void saveAll(List<Store> stores) {
        storeRepository.saveAll(stores.stream().map(Mapper::toDTO).toList());
    }
}