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

    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Restaurant restaurant = checkRestaurant(createOrderCommand);
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, restaurant);
        Order saveOrder = saveOrder(order);
        log.info("order is create with id: {}", saveOrder.getId().getValue());
        return orderDataMapper.orderToCreateOrderResponse(saveOrder);
    }

    private Restaurant checkRestaurant(CreateOrderCommand createOrderCommand) {
        Restaurant restaurant = orderDataMapper.createOrderCoommandToRestaurant(createOrderCommand);
        Optional<Restaurant> res = restaurantRepository.findRestaurantInformation(restaurant);
        if (res.isEmpty()){
            log.warn("could not find restaurant with restaurant id: {}", createOrderCommand.getRestaurantId());
            throw new OrderDomainException("could not find restaurant with restaurant id: "+createOrderCommand.getRestaurantId());
        }
        return res.get();
    }

    private void checkCustomer(UUID customerId) {
        Optional<Customer> customer = customerRepository.findCustomer(customerId);
        if (customer.isEmpty()){
            log.warn("could not find customer with customer id: {}", customer);
            throw new OrderDomainException("could not find customer with customer id: "+customer);
        }
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("could not save order!");
            throw  new OrderDomainException("could not save order!");
        }
        log.info("order is saved with id: {}", orderResult.getId().getValue());
        return orderResult;
    }
}
