package com.cleveritgroup.orderdivider.repository;

import com.cleveritgroup.orderdivider.entity.DeliveryBundle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryBundleRepository extends MongoRepository<DeliveryBundle, String> {
}
