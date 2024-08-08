package com.cleveritgroup.orderdivider.framework.adapter;

import com.cleveritgroup.orderdivider.core.domain.DeliveryCost;
import com.cleveritgroup.orderdivider.core.port.DeliveryCostRepositoryPort;
import com.cleveritgroup.orderdivider.framework.repository.DeliveryCostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryCostRepositoryAdapter implements DeliveryCostRepositoryPort {

    private final DeliveryCostRepository deliveryCostRepository;

    @Override
    public List<DeliveryCost> findDeliveryCosts(List<String> fromZoneIds, String zoneId) {
        return deliveryCostRepository.findDeliveryCosts(fromZoneIds, zoneId);
    }
}