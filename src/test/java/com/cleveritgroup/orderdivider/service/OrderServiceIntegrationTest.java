package com.cleveritgroup.orderdivider.service;

import com.cleveritgroup.orderdivider.TestcontainersConfiguration;
import com.cleveritgroup.orderdivider.entity.DeliveryBundle;
import com.cleveritgroup.orderdivider.entity.DeliveryState;
import com.cleveritgroup.orderdivider.entity.Item;
import com.cleveritgroup.orderdivider.entity.Order;
import com.cleveritgroup.orderdivider.repository.DeliveryBundleRepository;
import com.cleveritgroup.orderdivider.repository.DeliveryCostRepository;
import com.cleveritgroup.orderdivider.repository.OrderRepository;
import com.cleveritgroup.orderdivider.repository.StoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@Import(TestcontainersConfiguration.class)
public class OrderServiceIntegrationTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private DeliveryCostRepository deliveryCostRepository;

    @Autowired
    private DeliveryBundleRepository deliveryBundleRepository;

    @Nested
    @DisplayName("Fully Deliverable Order")
    class FullyDeliverableOrderTests {

        @BeforeEach
        void setUp() {
            storeRepository.saveAll(List.of(
                TestDataFactory.StoreFactory.createStore1(),
                TestDataFactory.StoreFactory.createStore2(),
                TestDataFactory.StoreFactory.createStore3()
            ));
            deliveryCostRepository.saveAll(TestDataFactory.DeliveryCostFactory.createDeliveryCosts(
            ));
        }

        @Test
        @DisplayName("Order can be fully delivered from multiple stores")
        void orderCanBeFullyDelivered() {
            List<Item> items = List.of(
                TestDataFactory.OrderFactory.getItem1(),
                TestDataFactory.OrderFactory.getItem2(),
                TestDataFactory.OrderFactory.getItem3()
            );
            Order order = TestDataFactory.OrderFactory.createOrderWithItems(items);

            List<DeliveryBundle> bundles = orderService.splitOrder(order);

            assertNotNull(bundles);
            assertEquals(1, bundles.size());
            assertTrue(bundles.stream().allMatch(bundle -> bundle.getState() == DeliveryState.ASSIGNED_TO_STORE));
        }
    }

    @Nested
    @DisplayName("Partially Deliverable Order")
    class PartiallyDeliverableOrderTests {

        @BeforeEach
        void setUp() {
            storeRepository.saveAll(List.of(
                TestDataFactory.StoreFactory.createStore4(),
                TestDataFactory.StoreFactory.createStore6()
            ));
            deliveryCostRepository.saveAll(TestDataFactory.DeliveryCostFactory.createDeliveryCosts(
            ));
        }

        @Test
        @DisplayName("Order items split across multiple stores")
        void orderItemsSplitAcrossStores() {
            List<Item> items = List.of(
                TestDataFactory.OrderFactory.getItem1(),
                TestDataFactory.OrderFactory.getItem2(),
                TestDataFactory.OrderFactory.getItem3()
            );
            Order order = TestDataFactory.OrderFactory.createOrderWithItems(items);

            List<DeliveryBundle> bundles = orderService.splitOrder(order);

            assertNotNull(bundles);
            assertEquals(2, bundles.size());
            assertTrue(bundles.stream().allMatch(bundle -> bundle.getState() == DeliveryState.ASSIGNED_TO_STORE));
        }
    }

    @Nested
    @DisplayName("Order with Some Items Missing")
    class SomeItemsMissingOrderTests {

        @BeforeEach
        void setUp() {
            storeRepository.saveAll(List.of(
                TestDataFactory.StoreFactory.createStore8()
            ));
            deliveryCostRepository.saveAll(TestDataFactory.DeliveryCostFactory.createDeliveryCosts());
        }

        @Test
        @DisplayName("Order with some items missing")
        void orderWithSomeItemsMissing() {
            List<Item> items = List.of(
                TestDataFactory.OrderFactory.getItem1(),
                TestDataFactory.OrderFactory.getItem2(),
                TestDataFactory.OrderFactory.getItem3()
            );
            Order order = TestDataFactory.OrderFactory.createOrderWithItems(items);

            List<DeliveryBundle> bundles = orderService.splitOrder(order);

            assertNotNull(bundles);
            assertEquals(2, bundles.size());
            assertTrue(bundles.stream().anyMatch(bundle -> bundle.getState() == DeliveryState.OUT_OF_STOCK));
        }
    }

    @Nested
    @DisplayName("Order with No Items Deliverable")
    class NoItemsDeliverableOrderTests {

        @BeforeEach
        void setUp() {
            storeRepository.saveAll(List.of(
                TestDataFactory.StoreFactory.createStore5()
            ));
            deliveryCostRepository.saveAll(TestDataFactory.DeliveryCostFactory.createDeliveryCosts(
            ));
        }

        @Test
        @DisplayName("Order with no items deliverable")
        void orderWithNoItemsDeliverable() {
            List<Item> items = List.of(
                TestDataFactory.OrderFactory.getItem1(),
                TestDataFactory.OrderFactory.getItem2(),
                TestDataFactory.OrderFactory.getItem3()
            );
            Order order = TestDataFactory.OrderFactory.createOrderWithItems(items);

            List<DeliveryBundle> bundles = orderService.splitOrder(order);

            assertNotNull(bundles);
            assertEquals(1, bundles.size());
            assertEquals(DeliveryState.OUT_OF_STOCK, bundles.get(0).getState());
        }
    }
}