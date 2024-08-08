package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.DeliveryCost;
import com.cleveritgroup.orderdivider.core.port.DeliveryCostRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.DeliveryCostDto;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DeliveryCostRepositoryAdapter implements DeliveryCostRepositoryPort {

    private final DeliveryCostRepository deliveryCostRepository;

    @Override
    public List<DeliveryCost> findDeliveryCosts(List<String> fromZoneIds, String zoneId) {
        return deliveryCostRepository.findDeliveryCosts(fromZoneIds, zoneId).stream().map(Mapper::toDomain).toList();
    }

    @Override
    public void saveAll(List<DeliveryCost> deliveryCosts) {
        deliveryCostRepository.saveAll(deliveryCosts.stream().map(Mapper::toDTO).toList());
    }
}