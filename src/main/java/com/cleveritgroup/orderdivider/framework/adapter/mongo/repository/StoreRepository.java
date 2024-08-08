package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.StoreDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Set;

public interface StoreRepository extends MongoRepository<StoreDto, String> {

    @Query("{ 'stock.sku': { $in: ?0 } }")
    List<StoreDto> findStoreWithSkus(Set<String> orderItemSkus);
}
