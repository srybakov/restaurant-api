package com.srybakov.restaurant.service.impl;

import com.srybakov.restaurant.domain.model.Restaurant;
import com.srybakov.restaurant.domain.model.User;
import com.srybakov.restaurant.domain.model.UserRole;
import com.srybakov.restaurant.domain.model.UserRoleEnum;
import com.srybakov.restaurant.domain.model.Vote;
import com.srybakov.restaurant.domain.repository.UserRepository;
import com.srybakov.restaurant.domain.repository.VoteRepository;
import com.srybakov.restaurant.exception.RoleDoesNotExsistException;
import com.srybakov.restaurant.exception.UserAlreadyExistException;
import com.srybakov.restaurant.exception.UserNameMandatoryException;
import com.srybakov.restaurant.exception.UserPasswordMandatoryException;
import com.srybakov.restaurant.exception.UserRoleMandatoryException;
import com.srybakov.restaurant.service.RestaurantService;
import com.srybakov.restaurant.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Service
public class UserServiceImpl implements UserService {

    private static final int CANNOT_VOTE_AFTER_HOUR = 11;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    @Transactional(readOnly = false)
    public User createUser(String name, String password, List<String> roles) throws UserRoleMandatoryException,
        UserPasswordMandatoryException, UserNameMandatoryException, RoleDoesNotExsistException,
        UserAlreadyExistException {

        verifyParams(name, password, roles);
        List<UserRoleEnum> parsedRoles = parseRoles(roles);

        User user = userRepository.findByName(name);
        if (user != null){
            throw new UserAlreadyExistException();
        } else {
            user = saveUser(name, password, parsedRoles);
        }
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Vote findUserVoteForToday(String username){
        return voteRepository.findUserVoteForToday(username);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserCanVoteToday(String username) {
        Vote userVote = findUserVoteForToday(username);
        if (userVote == null) {
            return true;
        } else {
            int dayHour = new DateTime().getHourOfDay();
            if (dayHour < CANNOT_VOTE_AFTER_HOUR){
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserVotedForThisRestaurantToday(String username, String restaurantName) {
        Vote userVote = findUserVoteForToday(username);
        return userVote != null && userVote.getRestaurant().getName().equals(restaurantName);
    }


    @Override
    public void voteForRestaurant(String username, String restaurantName) {
        Vote userVote = findUserVoteForToday(username);
        if (userVote == null){
            userVote = new Vote();
        }
        userVote.setVoteDate(new Date());
        userVote.setUser(userRepository.findByName(username));
        userVote.setRestaurant(restaurantService.findByName(restaurantName));
        voteRepository.save(userVote);
    }

    private User saveUser(String name, String password, List<UserRoleEnum> roleEnums){
        User user = new User();
        Set<UserRole> userRoles = new HashSet<>();
        for (UserRoleEnum userRoleEnum : roleEnums){
            UserRole userRole = new UserRole();
            userRole.setName(userRoleEnum.toString());
            userRole.setUser(user);
            userRoles.add(userRole);
        }
        user.setName(name);
        user.setPassword(password);
        user.setRoles(userRoles);
        userRepository.save(user);
        return user;
    }

    private List<UserRoleEnum> parseRoles(List<String> roles) throws RoleDoesNotExsistException {
        List<UserRoleEnum> parsedRoles = new ArrayList<>();
        for (String role : roles){
            if (UserRoleEnum.ADMIN.toString().equals(role)) {
                parsedRoles.add(UserRoleEnum.ADMIN);
            } else if (UserRoleEnum.USER.toString().equals(role)) {
                parsedRoles.add(UserRoleEnum.USER);
            } else {
                throw new RoleDoesNotExsistException("Role: " + role + " does not exist");
            }
        }
        return parsedRoles;
    }

    private void verifyParams(String name, String password, List<String> roles) throws UserNameMandatoryException,
        UserPasswordMandatoryException, UserRoleMandatoryException {

        if (StringUtils.isEmpty(name)){
            throw new UserNameMandatoryException();
        } else if (StringUtils.isEmpty(password)){
            throw new UserPasswordMandatoryException();
        } else if (roles.isEmpty()){
            throw new UserRoleMandatoryException();
        }
    }
}
