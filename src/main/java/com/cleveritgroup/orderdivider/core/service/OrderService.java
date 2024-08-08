package com.cleveritgroup.orderdivider.core.service;

import com.cleveritgroup.orderdivider.core.domain.*;
import com.cleveritgroup.orderdivider.core.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepositoryPort orderRepositoryPort;
    private final StoreRepositoryPort storeRepositoryPort;
    private final DeliveryCostRepositoryPort deliveryCostRepositoryPort;
    private final DeliveryBundleRepositoryPort deliveryBundleRepositoryPort;

    public List<DeliveryBundle> splitOrder(Order order) {
        List<Store> stores = getStoresWithItems(order);
        List<DeliveryCost> deliveryCosts = calculateDeliveryCosts(stores, order);
        List<DeliveryBundle> bundles = generateDeliveryBundles(deliveryCosts, order,stores);

        orderRepositoryPort.save(order);
        deliveryBundleRepositoryPort.saveAll(bundles);

        return bundles;
    }

    private List<Store> getStoresWithItems(Order order) {
        Set<String> orderItemSkus = order.getItems()
                .stream()
                .map(Item::sku)
                .collect(Collectors.toSet());

        return storeRepositoryPort.findStoreWithSkus(orderItemSkus);
    }

    private List<DeliveryCost> calculateDeliveryCosts(List<Store> stores, Order order) {
        List<String> fromZoneIds = stores.stream()
                .map(Store::address)
                .map(Address::zoneId)
                .collect(Collectors.toList());

        return deliveryCostRepositoryPort.findDeliveryCosts(fromZoneIds, order.deliveryAddress()
                .zoneId());
    }

    private List<DeliveryBundle> generateDeliveryBundles(List<DeliveryCost> deliveryCosts, Order order,List<Store> stores) {
        Map<Store, Integer> storeWithCost = getStoreWithCost(deliveryCosts, stores);
        List<Item> orderItems = List.copyOf(order.getItems());
        List<DeliveryBundle> deliveryBundles = new ArrayList<>();

        while(!orderItems.isEmpty()) {
            Pair storeWithMaxItemsAndCost = getStoreWithMaxItemsAndCost(storeWithCost, orderItems);
            Store store = storeWithMaxItemsAndCost.key();
            if(store == null){
                break;
            }

            List<Item> storeItems = getCommonItems(store, orderItems);
            orderItems = orderItems.stream().filter(item -> storeItems.stream().noneMatch(si -> si.sku().equals(item.sku()))).toList();
            DeliveryBundle bundle = createDeliveryBundle(storeItems, storeWithMaxItemsAndCost, DeliveryState.ASSIGNED_TO_STORE,order);
            deliveryBundles.add(bundle);
            storeWithCost.remove(store);
        }

        if(!orderItems.isEmpty()) {
            DeliveryBundle outOfStockBundle = createDeliveryBundle(orderItems, new Pair(), DeliveryState.OUT_OF_STOCK, order);
            deliveryBundles.add(outOfStockBundle);
        }

        return deliveryBundles;
    }

    private Map<Store, Integer> getStoreWithCost(List<DeliveryCost> deliveryCosts, List<Store> stores) {
        Map<Store, Integer> storeWithCost = new HashMap<>();
        for (Store store : stores) {
            String storeZoneId = store.address()
                    .zoneId();
            Optional<DeliveryCost> optionalDeliveryCost = deliveryCosts.stream()
                    .filter(cost -> cost.fromZoneId()
                            .equals(storeZoneId))
                    .findFirst();
            optionalDeliveryCost.ifPresent(deliveryCost -> storeWithCost.put(store, deliveryCost.cost()));
        }
        return storeWithCost;
    }

    private DeliveryBundle createDeliveryBundle(List<Item> storeItems, Pair costStore, DeliveryState deliveryState,Order order) {
        return DeliveryBundle.builder()
                .items( storeItems.stream()
                        .map(i -> new ItemBundle(i, Optional.ofNullable(costStore.key()).map(Store::storeId).orElse(""))).toList())
                .orderId(order.orderId())
                .deliveryAddress(order.deliveryAddress())
                .deliveryCost(costStore.value())
                .state(deliveryState)
                .buyer(order.buyer())
                .build();
    }

    private List<Item> getCommonItems(Store store, List<Item> orderItems) {
        List<Item> commonItems = new ArrayList<>();
        for (Item orderItem : orderItems) {
            for (Stock storeStock : store.stock()) {
                if (orderItem.sku()
                        .equals(storeStock.sku())) {
                    Item commonItem = Item.builder()
                            .sku(orderItem.sku())
                            .quantity(Math.min(orderItem.quantity(), storeStock.quantity()))
                            .price(orderItem.price())
                            .build();
                    commonItems.add(commonItem);
                    break;
                }
            }
        }
        return commonItems;
    }

    private Pair getStoreWithMaxItemsAndCost(Map<Store, Integer> storeWithCost, List<Item> orderItems) {
        Pair maxPair = null;
        int maxItems = 0;
        for (Map.Entry<Store, Integer> entry : storeWithCost.entrySet()) {
            Store store = entry.getKey();
            Integer cost = entry.getValue();
            int itemsInStore = 0;
            for (Item orderItem : orderItems) {
                for (Stock stockItem : store.stock()) {
                    if (orderItem.sku()
                            .equals(stockItem.sku())) {
                        itemsInStore += Math.min(orderItem.quantity(), stockItem.quantity());
                    }
                }
            }

            if (itemsInStore > maxItems) {
                maxPair = new Pair(store, cost);
                maxItems = itemsInStore;
            } else if (itemsInStore == maxItems) {
                if (maxPair != null && maxPair.value() > cost) {
                    maxPair = new Pair(store, cost);
                }
            }
        }

        if(maxPair==null){
            maxPair = new Pair();
        }

        return maxPair;
    }

    private record Pair(Store key, Integer value) {
        public Pair() {
            this(null, 0);
        }
    }
}