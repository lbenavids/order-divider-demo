package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.DeliveryBundleDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryBundleRepository extends MongoRepository<DeliveryBundleDto, String> {
}
