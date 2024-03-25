package com.food.ordering.order.service.domain.ports.outputs.repository;

import com.food.ordering.order.domain.entity.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {

    Optional<Restaurant> findRestaurantInformation(Restaurant restaurant);
}
