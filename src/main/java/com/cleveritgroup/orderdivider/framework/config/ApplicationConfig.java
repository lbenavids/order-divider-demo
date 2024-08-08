package com.cleveritgroup.orderdivider.framework.config;

import com.cleveritgroup.orderdivider.core.port.DeliveryBundleRepositoryPort;
import com.cleveritgroup.orderdivider.core.port.DeliveryCostRepositoryPort;
import com.cleveritgroup.orderdivider.core.port.OrderRepositoryPort;
import com.cleveritgroup.orderdivider.core.port.StoreRepositoryPort;
import com.cleveritgroup.orderdivider.framework.adapter.DeliveryBundleRepositoryAdapter;
import com.cleveritgroup.orderdivider.framework.adapter.DeliveryCostRepositoryAdapter;
import com.cleveritgroup.orderdivider.framework.adapter.OrderRepositoryAdapter;
import com.cleveritgroup.orderdivider.framework.adapter.StoreRepositoryAdapter;
import com.cleveritgroup.orderdivider.framework.repository.DeliveryBundleRepository;
import com.cleveritgroup.orderdivider.framework.repository.DeliveryCostRepository;
import com.cleveritgroup.orderdivider.framework.repository.OrderRepository;
import com.cleveritgroup.orderdivider.framework.repository.StoreRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public StoreRepositoryPort storeRepositoryPort(StoreRepository storeRepository) {
        return new StoreRepositoryAdapter(storeRepository);
    }

    @Bean
    public DeliveryBundleRepositoryPort deliveryBundleRepositoryPort(DeliveryBundleRepository deliveryBundleRepository) {
        return new DeliveryBundleRepositoryAdapter(deliveryBundleRepository);
    }

    @Bean
    public DeliveryCostRepositoryPort deliveryCostRepositoryPort(DeliveryCostRepository deliveryCostRepository) {
        return new DeliveryCostRepositoryAdapter(deliveryCostRepository);
    }

    @Bean
    public OrderRepositoryPort orderRepositoryPort(OrderRepository orderRepository) {
        return new OrderRepositoryAdapter(orderRepository);
    }
}