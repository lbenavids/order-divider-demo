package com.cleveritgroup.orderdivider.repository;

import com.cleveritgroup.orderdivider.entity.Store;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface StoreRepository extends MongoRepository<Store, String> {

    @Query("{ 'stock.sku': { $in: ?0 } }")
    List<Store> findStoreWithSkus(Set<String> orderItemSkus);
}
