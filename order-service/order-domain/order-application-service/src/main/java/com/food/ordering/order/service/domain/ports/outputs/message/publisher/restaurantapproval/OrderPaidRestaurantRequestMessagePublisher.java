package com.food.ordering.order.service.domain.ports.outputs.message.publisher.restaurantapproval;

import com.food.ordering.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.order.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
