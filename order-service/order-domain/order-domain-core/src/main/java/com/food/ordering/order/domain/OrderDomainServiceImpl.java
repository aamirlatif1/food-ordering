package com.food.ordering.order.domain;

import com.food.ordering.order.domain.entity.Order;
import com.food.ordering.order.domain.entity.Product;
import com.food.ordering.order.domain.entity.Restaurant;
import com.food.ordering.order.domain.event.OrderCancelledEvent;
import com.food.ordering.order.domain.event.OrderCreatedEvent;
import com.food.ordering.order.domain.event.OrderPaidEvent;
import com.food.ordering.order.domain.exception.OrderDomainException;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService{

    public static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id: {} is initialized", order.getId().getValue());
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id: {} is paid", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id: {} is approved", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initializeOrder();
        log.info("Order payment is cancelling for order id: {} ", order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessage) {
        order.cancel(failureMessage);
        log.info("Order with id: {} is cancelled", order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if(restaurant.isActive()){
            throw new OrderDomainException("Restaurant with id "+restaurant.getId().getValue()+
                    " is currently not active!");
        }
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(item -> restaurant.getProducts().forEach(restauntProduct -> {
            Product currentProduct = item.getProduct();
            if (currentProduct.equals(restauntProduct)){
                currentProduct.updateWithConfirmedNameAndPrice(restauntProduct.getName(), restauntProduct.getPrice());
            }
        }));
    }
}
