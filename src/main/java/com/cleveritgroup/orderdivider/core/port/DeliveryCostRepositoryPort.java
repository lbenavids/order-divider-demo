package com.cleveritgroup.orderdivider.core.port;

import com.cleveritgroup.orderdivider.core.domain.DeliveryCost;

import java.util.List;

public interface DeliveryCostRepositoryPort {
    List<DeliveryCost> findDeliveryCosts(List<String> fromZoneIds, String zoneId);

    void saveAll(List<DeliveryCost> deliveryCosts);
}