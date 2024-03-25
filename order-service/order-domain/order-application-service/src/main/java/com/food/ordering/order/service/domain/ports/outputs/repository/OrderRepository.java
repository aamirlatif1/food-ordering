package com.food.ordering.order.service.domain.ports.outputs.repository;

import com.food.ordering.order.domain.entity.Order;
import com.food.ordering.order.domain.valueobject.TrackingId;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findByTrackingId(TrackingId trackingId);
}
