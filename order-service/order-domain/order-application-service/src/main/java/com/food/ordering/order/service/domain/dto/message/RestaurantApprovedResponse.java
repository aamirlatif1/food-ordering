package com.food.ordering.order.service.domain.dto.message;

import com.food.ordering.domain.valueobject.OrderApprovedStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantApprovedResponse {
    private String id;
    private String sageId;
    private String orderId;
    private String restaurantId;
    private Instant createdAt;
    private OrderApprovedStatus orderApprovedStatus;
    private List<String> failureMessages;
}
