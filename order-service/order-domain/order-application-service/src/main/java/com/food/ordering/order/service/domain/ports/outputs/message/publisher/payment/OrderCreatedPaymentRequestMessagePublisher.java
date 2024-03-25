package com.food.ordering.order.service.domain.ports.outputs.message.publisher.payment;

import com.food.ordering.domain.event.publisher.DomainEventPublisher;
import com.food.ordering.order.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {

}
