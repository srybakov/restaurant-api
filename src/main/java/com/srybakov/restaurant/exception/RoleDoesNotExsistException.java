package com.srybakov.restaurant.exception;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public class RoleDoesNotExsistException extends Exception {

    public RoleDoesNotExsistException(String message) {
        super(message);
    }
}
