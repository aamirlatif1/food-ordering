package com.food.ordering.order.service.domain.dto.message;

import com.food.ordering.domain.valueobject.PaymentStatus;
import com.food.ordering.domain.valueobject.ProductId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PaymentResponse {
    private String id;
    private String sageId;
    private String orderId;
    private String paymentId;
    private BigDecimal price;
    private Instant createAt;
    private PaymentStatus paymentStatus;
    private List<String> failureMessages;
}
