package com.food.ordering.order.service.domain;

import com.food.ordering.order.domain.entity.Order;
import com.food.ordering.order.domain.exception.OrderNotFoundException;
import com.food.ordering.order.domain.valueobject.TrackingId;
import com.food.ordering.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.order.service.domain.mapper.OrderDataMapper;
import com.food.ordering.order.service.domain.ports.outputs.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;


    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()) {
            log.warn("could not find order with tracking id: {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("could not find order with tracking id: "
                    + trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
