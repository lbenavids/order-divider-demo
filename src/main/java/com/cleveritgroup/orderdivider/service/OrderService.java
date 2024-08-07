package com.cleveritgroup.orderdivider.service;

import com.cleveritgroup.orderdivider.entity.Address;
import com.cleveritgroup.orderdivider.entity.DeliveryBundle;
import com.cleveritgroup.orderdivider.entity.DeliveryCost;
import com.cleveritgroup.orderdivider.entity.DeliveryState;
import com.cleveritgroup.orderdivider.entity.Item;
import com.cleveritgroup.orderdivider.entity.ItemBundle;
import com.cleveritgroup.orderdivider.entity.Order;
import com.cleveritgroup.orderdivider.entity.Stock;
import com.cleveritgroup.orderdivider.entity.Store;
import com.cleveritgroup.orderdivider.repository.DeliveryBundleRepository;
import com.cleveritgroup.orderdivider.repository.DeliveryCostRepository;
import com.cleveritgroup.orderdivider.repository.OrderRepository;
import com.cleveritgroup.orderdivider.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {


    private final OrderRepository orderRepository;
    private StoreRepository storeRepository;

    private DeliveryCostRepository deliveryCostRepository;

    private DeliveryBundleRepository deliveryBundleRepository;

    public List<DeliveryBundle> splitOrder(Order order) {
        // 1. get the list of stores that have all the items for the order
        List<Store> stores = getStoresWithItems(order);

        // 2. for each store calculate the delivery cost from the store to the order's address
        List<DeliveryCost> deliveryCosts = calculateDeliveryCosts(stores, order);

        // 3. generate the delivery bundles for the stores with the lowest cost
        List<DeliveryBundle> bundles = generateDeliveryBundles(deliveryCosts, order,stores);

        int orderAmount= 0;
        for (Item item : order.getItems()) {
            item.setTotalAmount(item.getQuantity() * item.getPrice());
            orderAmount+=item.getTotalAmount();
        }
        order.setTotalAmount(orderAmount);
        // 4. save new bundles to the DB
        orderRepository.save(order);
        deliveryBundleRepository.saveAll(bundles);

        return bundles;
    }

    @Data
    private static class Pair{
        private Store key;
        private Integer value =0;
    }

    private List<DeliveryBundle> generateDeliveryBundles(List<DeliveryCost> deliveryCosts, Order order,List<Store> stores) {


        Map<Store, Integer> storeWithCost = getStoreWithCost(deliveryCosts, stores);

        // Separate the items in the order
        List<Item> orderItems = List.copyOf(order.getItems());

        List<DeliveryBundle> deliveryBundles = new ArrayList<>();

        while(!orderItems.isEmpty()) {
            // Identify the store with the maximum items and the costs
            // This should provide a Pair object with store and its corresponding delivery cost.
            Pair storeWithMaxItemsAndCost = getStoreWithMaxItemsAndCost(storeWithCost, orderItems);

            // Get the store and cost from the pair
            Store store = storeWithMaxItemsAndCost.getKey();
            if(store == null){
                break;
            }

            // Identify the items from this store that can be delivered
            // Get only those items that are available in this store and in the order list
            List<Item> storeItems = getCommonItems(store, orderItems);

            // Remove these items from our main order items list
            // the orderItems is not removing the items from the list so that can you reassign the items to the out of stock bundle
            orderItems = orderItems.stream().filter(item -> storeItems.stream().noneMatch(si -> si.getSku().equals(item.getSku()))).toList();

            // Create a bundle for these items
            DeliveryBundle bundle = createDeliveryBundle(storeItems, storeWithMaxItemsAndCost, DeliveryState.ASSIGNED_TO_STORE,order);
            deliveryBundles.add(bundle);

            // Remove this store from our main list
            storeWithCost.remove(store);
        }

        // If there are any items left over, we put them in a separate bundle with out of stock status
        if(!orderItems.isEmpty()) {
            DeliveryBundle outOfStockBundle = createDeliveryBundle(orderItems, new Pair(), DeliveryState.OUT_OF_STOCK, order);
            deliveryBundles.add(outOfStockBundle);
        }

        return deliveryBundles;
    }

    private Map<Store, Integer> getStoreWithCost(List<DeliveryCost> deliveryCosts, List<Store> stores) {
        Map<Store, Integer> storeWithCost = new HashMap<>();
        for (Store store : stores) {
            String storeZoneId = store.getAddress()
                    .getZoneId();
            Optional<DeliveryCost> optionalDeliveryCost = deliveryCosts.stream()
                    .filter(cost -> cost.getFromZoneId()
                            .equals(storeZoneId))
                    .findFirst();
            optionalDeliveryCost.ifPresent(deliveryCost -> storeWithCost.put(store, deliveryCost
                    .getCost()));
        }
        return storeWithCost;
    }

    private DeliveryBundle createDeliveryBundle(List<Item> storeItems, Pair costStore, DeliveryState deliveryState,Order order) {
        return DeliveryBundle.builder()
                .items( storeItems.stream()
                        .map(i -> new ItemBundle(i, Optional.ofNullable(costStore.getKey()).map(Store::getStoreId).orElse(""))).toList())
                .orderId(order.getOrderId())
                .deliveryAddress(order.getDeliveryAddress())
                .deliveryCost(costStore.getValue())
                .state(deliveryState)
                .buyer(order.getBuyer())
                .build();
    }

    private List<Item> getCommonItems(Store store, List<Item> orderItems) {

        List<Item> commonItems = new ArrayList<>();
        for (Item orderItem : orderItems) {
            for (Stock storeStock : store.getStock()) {
                if (orderItem.getSku()
                        .equals(storeStock.getSku())) {
                    Item commonItem = new Item();
                    commonItem.setSku(orderItem.getSku());
                    commonItem.setQuantity(Math.min(orderItem.getQuantity(), storeStock.getQuantity()));
                    commonItem.setPrice(orderItem.getPrice()); // assuming price is in order item
                    commonItem.setTotalAmount(commonItem.getPrice() * commonItem.getQuantity());
                    commonItems.add(commonItem);
                    break; // Break inner loop as we already found common sku in store stock
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
                for (Stock stockItem : store.getStock()) {
                    if (orderItem.getSku()
                            .equals(stockItem.getSku())) {
                        itemsInStore += Math.min(orderItem.getQuantity(), stockItem.getQuantity());
                    }
                }
            }

            if (itemsInStore > maxItems) {
                maxPair = new Pair();
                maxPair.setKey(store);
                maxPair.setValue(cost);
                maxItems = itemsInStore;
            } else if (itemsInStore == maxItems) {
                if (maxPair !=null && maxPair.getValue() > cost) {
                    maxPair.setKey(store);
                    maxPair.setValue(cost);
                }
            }
        }

        if(maxPair==null){
            maxPair = new Pair();
        }

        return maxPair;
    }

    private List<DeliveryCost>  calculateDeliveryCosts(List<Store> stores, Order order) {
        List<String> fromZoneIds = stores.stream()
                .map(Store::getAddress)
                .map(Address::getZoneId)
                .collect(Collectors.toList());

        return deliveryCostRepository.findDeliveryCosts(fromZoneIds, order.getDeliveryAddress().getZoneId());
    }

    private List<Store> getStoresWithItems(Order order) {
        Set<String> orderItemSkus = order.getItems().stream()
                .map(Item::getSku)
                .collect(Collectors.toSet());

        return storeRepository.findStoreWithSkus(orderItemSkus);

    }
}
