package com.cleveritgroup.orderdivider.repository;

import com.cleveritgroup.orderdivider.entity.DeliveryCost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeliveryCostRepository extends MongoRepository<DeliveryCost, String> {
    @Query("{ 'fromZoneId': { $in: ?0 }, 'toZoneId': ?1 }")
    List<DeliveryCost> findDeliveryCosts(List<String> fromZoneIds, String zoneId);
}
