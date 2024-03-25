package com.food.ordering.order.service.domain.ports.outputs.repository;

import com.food.ordering.order.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
