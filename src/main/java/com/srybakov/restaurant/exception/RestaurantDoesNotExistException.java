package com.srybakov.restaurant.exception;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public class RestaurantDoesNotExistException extends Exception {

    public RestaurantDoesNotExistException(String message) {
        super(message);
    }
}
