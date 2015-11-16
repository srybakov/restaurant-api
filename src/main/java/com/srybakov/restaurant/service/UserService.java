package com.srybakov.restaurant.service;

import com.srybakov.restaurant.domain.model.User;
import com.srybakov.restaurant.domain.model.Vote;
import com.srybakov.restaurant.exception.RoleDoesNotExsistException;
import com.srybakov.restaurant.exception.UserAlreadyExistException;
import com.srybakov.restaurant.exception.UserNameMandatoryException;
import com.srybakov.restaurant.exception.UserPasswordMandatoryException;
import com.srybakov.restaurant.exception.UserRoleMandatoryException;

import java.util.List;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public interface UserService {

    User createUser(String name, String password, List<String> roles) throws UserRoleMandatoryException,
            UserPasswordMandatoryException, UserNameMandatoryException, RoleDoesNotExsistException,
            UserAlreadyExistException;

    Vote findUserVoteForToday(String username);

    boolean isUserCanVoteToday(String username);

    boolean isUserVotedForThisRestaurantToday(String username, String restaurantName);

    void voteForRestaurant(String username, String restaurantName);
}
