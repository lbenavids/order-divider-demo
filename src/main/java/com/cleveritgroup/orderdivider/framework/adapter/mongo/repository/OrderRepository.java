package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.OrderDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDto, String> {
}
