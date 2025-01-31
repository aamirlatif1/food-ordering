package com.food.ordering.order.service.domain;

import com.food.ordering.order.service.domain.dto.create.CreateOrderCommand;
import com.food.ordering.order.service.domain.dto.create.CreateOrderResponse;
import com.food.ordering.order.service.domain.dto.track.TrackOrderQuery;
import com.food.ordering.order.service.domain.dto.track.TrackOrderResponse;
import com.food.ordering.order.service.domain.ports.inputs.service.OrderApplicationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@AllArgsConstructor
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;
    private final OrderTrackCommandHandler orderTrackCommandHandler;

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return null;
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return null;
    }
}
