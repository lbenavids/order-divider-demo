package com.cleveritgroup.orderdivider.repository;

import com.cleveritgroup.orderdivider.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
