package com.food.ordering.order.domain.valueobject;

import com.food.ordering.domain.valueobject.BaseId;

public class OrderItemId extends BaseId<Long> {
    public OrderItemId(Long value) {
        super(value);
    }
}
