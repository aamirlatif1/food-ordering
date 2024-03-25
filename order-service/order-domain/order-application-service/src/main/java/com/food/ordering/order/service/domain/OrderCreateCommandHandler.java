package com.food.ordering.order.service.domain;

import com.food.ordering.order.domain.OrderDomainService;
import com.food.ordering.order.domain.entity.Customer;
import com.food.ordering.order.domain.entity.Order;
import com.food.ordering.order.domain.entity.Restaurant;
import com.food.ordering.order.domain.event.OrderCreatedEvent;
import com.food.ordering.order.domain.exception.OrderDomainException;
import com.food.ordering.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.order.service.domain.ports.outputs.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.ordering.order.service.domain.ports.outputs.repository.CustomerRepository;
import com.food.ordering.order.service.domain.ports.outputs.repository.OrderRepository;
import com.food.ordering.order.service.domain.ports.outputs.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Component
public class OrderCreateCommandHandler {

    private final OrderCreateHelper orderCreateHelper;
    private final OrderDataMapper orderDataMapper;
    private final OrderCreatedPaymentRequestMessagePublisher publisher;

    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = orderCreateHelper.persistOrder(createOrderCommand);
        log.info("order is create with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        publisher.publish(orderCreatedEvent);
        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());
    }


}
