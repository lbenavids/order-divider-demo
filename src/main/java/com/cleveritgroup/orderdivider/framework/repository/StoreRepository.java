package com.cleveritgroup.orderdivider.framework.repository;

import com.cleveritgroup.orderdivider.core.domain.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface StoreRepository extends MongoRepository<Store, String> {

    @Query("{ 'stock.sku': { $in: ?0 } }")
    List<Store> findStoreWithSkus(Set<String> orderItemSkus);
}
