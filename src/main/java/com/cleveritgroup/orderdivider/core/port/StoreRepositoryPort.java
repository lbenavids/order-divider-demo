package com.cleveritgroup.orderdivider.core.port;


import com.cleveritgroup.orderdivider.core.domain.Store;

import java.util.List;
import java.util.Set;

public interface StoreRepositoryPort {
    List<Store> findStoreWithSkus(Set<String> orderItemSkus);

    void saveAll(List<Store> stores);
}