package com.food.ordering.order.service.domain;

import com.food.ordering.order.service.domain.dto.message.RestaurantApprovedResponse;
import com.food.ordering.order.service.domain.ports.inputs.message.listener.messageapproval.RestaurantApprovalResponseMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


@Slf4j
@Validated
@Service
public class RestaurantApprovalResponseMessageListenerImpl implements RestaurantApprovalResponseMessageListener {

    @Override
    public void orderApproved(RestaurantApprovedResponse restaurantApprovedResponse) {

    }

    @Override
    public void orderRejected(RestaurantApprovedResponse restaurantApprovedResponse) {

    }
}
