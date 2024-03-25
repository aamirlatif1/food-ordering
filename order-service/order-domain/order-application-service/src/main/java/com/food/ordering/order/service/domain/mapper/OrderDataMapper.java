package com.food.ordering.order.service.domain.mapper;

import com.food.ordering.domain.valueobject.CustomerId;
import com.food.ordering.domain.valueobject.Money;
import com.food.ordering.domain.valueobject.ProductId;
import com.food.ordering.domain.valueobject.RestaurantId;
import com.food.ordering.order.domain.entity.Order;
import com.food.ordering.order.domain.entity.Product;
import com.food.ordering.order.domain.entity.Restaurant;
import com.food.ordering.order.domain.valueobject.StreetAddress;
import com.food.ordering.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.order.service.domain.dto.create.OrderAddress;
import com.food.ordering.order.domain.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderDataMapper {
    public Restaurant createOrderCoommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream()
                        .map(orderItem -> new Product(new ProductId(orderItem.getProductId())))
                        .toList())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliverAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntities(createOrderCommand.getItems()))
                .build();
    }

    private List<OrderItem> orderItemsToOrderItemEntities(List<com.food.ordering.order.service.domain.dto.create.OrderItem> items) {
        return items.stream().map(orderItem ->
                OrderItem.builder()
                        .product(new Product(new ProductId(orderItem.getProductId())))
                        .price(new Money(orderItem.getPrice()))
                        .quantity(orderItem.getQuantity())
                        .subTotal(new Money(orderItem.getSubTotal()))
                        .build()).toList();
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(
                UUID.randomUUID(),
                address.getStreet(),
                address.getPostalCode(),
                address.getCity()
        );
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderTrackingId(order.getTrackingId().getValue())
                .orderStatus(order.getOrderStatus())
                .build();
    }
}
