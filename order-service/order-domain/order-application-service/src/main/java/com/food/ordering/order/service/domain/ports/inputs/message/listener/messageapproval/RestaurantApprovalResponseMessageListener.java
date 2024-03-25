package com.food.ordering.order.service.domain.ports.inputs.message.listener.messageapproval;

import com.food.ordering.order.service.domain.dto.message.RestaurantApprovedResponse;

public interface RestaurantApprovalResponseMessageListener {
    void orderApproved(RestaurantApprovedResponse restaurantApprovedResponse);
    void orderRejected(RestaurantApprovedResponse restaurantApprovedResponse);
}
