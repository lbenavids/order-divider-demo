package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.DeliveryCost;
import com.cleveritgroup.orderdivider.core.port.DeliveryCostRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.DeliveryCostDto;
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
        return deliveryCostRepository.findDeliveryCosts(fromZoneIds, zoneId).stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public void saveAll(List<DeliveryCost> deliveryCosts) {
        deliveryCostRepository.saveAll(deliveryCosts.stream().map(this::toDTO).toList());
    }

    private DeliveryCostDto toDTO(DeliveryCost deliveryCost) {
        return DeliveryCostDto.builder()
                .fromZoneId(deliveryCost.getFromZoneId())
                .toZoneId(deliveryCost.getToZoneId())
                .cost(deliveryCost.getCost())
                .build();
    }

    private DeliveryCost toDomain(DeliveryCostDto deliveryCostDto) {
        return DeliveryCost.builder()
                .fromZoneId(deliveryCostDto.getFromZoneId())
                .toZoneId(deliveryCostDto.getToZoneId())
                .cost(deliveryCostDto.getCost())
                .build();
    }
}