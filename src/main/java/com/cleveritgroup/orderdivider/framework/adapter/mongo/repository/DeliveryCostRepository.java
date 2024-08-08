package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.DeliveryCostDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeliveryCostRepository extends MongoRepository<DeliveryCostDto, String> {
    @Query("{ 'fromZoneId': { $in: ?0 }, 'toZoneId': ?1 }")
    List<DeliveryCostDto> findDeliveryCosts(List<String> fromZoneIds, String zoneId);
}
