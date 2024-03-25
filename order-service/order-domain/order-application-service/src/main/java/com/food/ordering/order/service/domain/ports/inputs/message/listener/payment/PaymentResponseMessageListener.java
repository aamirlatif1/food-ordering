package com.food.ordering.order.service.domain.ports.inputs.message.listener.payment;

import com.food.ordering.order.service.domain.dto.message.PaymentResponse;

public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
