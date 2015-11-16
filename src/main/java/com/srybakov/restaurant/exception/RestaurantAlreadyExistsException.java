package com.srybakov.restaurant.exception;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public class RestaurantAlreadyExistsException extends Exception {

    public RestaurantAlreadyExistsException(String message) {
        super(message);
    }
}
