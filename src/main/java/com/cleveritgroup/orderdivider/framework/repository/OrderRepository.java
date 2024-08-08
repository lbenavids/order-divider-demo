package com.cleveritgroup.orderdivider.framework.repository;

import com.cleveritgroup.orderdivider.core.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
