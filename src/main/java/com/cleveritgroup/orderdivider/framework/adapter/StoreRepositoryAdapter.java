package com.cleveritgroup.orderdivider.framework.adapter;

import com.cleveritgroup.orderdivider.core.domain.Store;
import com.cleveritgroup.orderdivider.core.port.StoreRepositoryPort;
import com.cleveritgroup.orderdivider.framework.repository.StoreRepository;
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
        return storeRepository.findStoreWithSkus(orderItemSkus);
    }
}