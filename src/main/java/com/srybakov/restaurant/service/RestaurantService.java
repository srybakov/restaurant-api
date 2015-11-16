package com.srybakov.restaurant.service;

import com.srybakov.restaurant.domain.model.Restaurant;
import com.srybakov.restaurant.exception.RestaurantAlreadyExistsException;
import com.srybakov.restaurant.exception.RestaurantDoesNotExistException;

import java.util.Map;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public interface RestaurantService {

    void createRestaurant(String name) throws RestaurantAlreadyExistsException;

    void inputCurrentMenu(String restaurantName, Map<String, Double> dishes)
            throws RestaurantDoesNotExistException;

    Restaurant findByName(String name);

    Map<Restaurant, Long> findMostVoted();

    Map<Restaurant, Long> findMostVotedForToday();

}
