package com.cleveritgroup.orderdivider.core.port;

import com.cleveritgroup.orderdivider.core.domain.DeliveryBundle;

import java.util.List;

public interface DeliveryBundleRepositoryPort {
    void saveAll(List<DeliveryBundle> bundles);
}