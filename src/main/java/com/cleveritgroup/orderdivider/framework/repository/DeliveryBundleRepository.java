package com.cleveritgroup.orderdivider.framework.repository;

import com.cleveritgroup.orderdivider.core.domain.DeliveryBundle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryBundleRepository extends MongoRepository<DeliveryBundle, String> {
}
