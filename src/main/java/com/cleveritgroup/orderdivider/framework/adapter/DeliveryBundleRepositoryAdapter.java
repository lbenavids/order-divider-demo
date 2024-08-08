package com.cleveritgroup.orderdivider.framework.adapter;

import com.cleveritgroup.orderdivider.core.domain.DeliveryBundle;
import com.cleveritgroup.orderdivider.core.port.DeliveryBundleRepositoryPort;
import com.cleveritgroup.orderdivider.framework.repository.DeliveryBundleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DeliveryBundleRepositoryAdapter implements DeliveryBundleRepositoryPort {

    private final DeliveryBundleRepository deliveryBundleRepository;

    @Override
    public void saveAll(List<DeliveryBundle> bundles) {
        deliveryBundleRepository.saveAll(bundles);
    }
}