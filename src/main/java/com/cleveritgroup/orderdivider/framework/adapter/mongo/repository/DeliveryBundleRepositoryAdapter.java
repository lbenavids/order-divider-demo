package com.cleveritgroup.orderdivider.framework.adapter.mongo.repository;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.core.port.DeliveryBundleRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.dto.DeliveryBundleDto;
import com.cleveritgroup.orderdivider.framework.adapter.mongo.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class DeliveryBundleRepositoryAdapter implements DeliveryBundleRepositoryPort {

    private final DeliveryBundleRepository deliveryBundleRepository;

    @Override
    public void saveAll(List<DeliveryBundle> bundles) {
        List<DeliveryBundleDto> bundleDtos = bundles.stream().map(Mapper::toDTO).collect(Collectors.toList());
        deliveryBundleRepository.saveAll(bundleDtos);
    }
}