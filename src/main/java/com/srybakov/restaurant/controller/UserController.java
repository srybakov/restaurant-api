package com.srybakov.restaurant.controller;

import com.srybakov.restaurant.domain.model.Restaurant;
import com.srybakov.restaurant.domain.webmodel.MostVotedRestaurantJson;
import com.srybakov.restaurant.domain.webmodel.ResponseJson;
import com.srybakov.restaurant.domain.webmodel.RestaurantJson;
import com.srybakov.restaurant.service.RestaurantService;
import com.srybakov.restaurant.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

import static com.srybakov.restaurant.ApplicationConstants.*;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Controller
@RequestMapping(value = "/user")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class UserController extends BaseController{

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public @ResponseBody ResponseJson voteWS(@RequestBody RestaurantJson voteRequest){
        ResponseJson response;
        if (StringUtils.isEmpty(voteRequest.getRestaurantName())){
            response = createResponse(EMPTY_RESTAURANT_NAME_CODE, RESTAURANT_NAME_MANDATORY_MESSAGE);
        } else if (restaurantService.findByName(voteRequest.getRestaurantName()) == null) {
            response = createResponse(RESTAURANT_DOES_NOT_EXIST_CODE, String.format(RESTAURANT_DOES_NOT_EXIST_MESSAGE,
                    voteRequest.getRestaurantName()));
        } else  {
            response = processVoteRequest(voteRequest);
        }
        return response;
    }

    @RequestMapping(value = "/getMostVotedRestaurant")
    public @ResponseBody MostVotedRestaurantJson getMostVotedRestaurantWS(){
        return prepareResult(restaurantService.findMostVoted());
    }

    @RequestMapping(value = "/getMostVotedForTodayRestaurant")
    public @ResponseBody MostVotedRestaurantJson getMostVotedForTodayRestaurantWS(){
        return prepareResult(restaurantService.findMostVotedForToday());
    }


    private ResponseJson processVoteRequest(RestaurantJson voteRequest){
        ResponseJson response;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        String restaurantName = voteRequest.getRestaurantName();
        if (!userService.isUserCanVoteToday(userName)){
            response = createResponse(USER_CANNOT_VOTE_TODAY_CODE, USER_CANNOT_VOTE_TODAY_MESSAGE);
        } else if (userService.isUserVotedForThisRestaurantToday(userName, restaurantName)){
            response = createResponse(USER_CANNOT_VOTE_TWICE_FOR_SAME_RESTAURANT_CODE,
                    USER_CANNOT_VOTE_TWICE_FOR_SAME_RESTAURANT_MESSAGE);
        } else {
            response = processVoteRequest(userName, restaurantName);
        }
        return response;
    }

    private ResponseJson processVoteRequest(String userName, String restaurantName){
        ResponseJson response;
        String successMsg;
        if (userService.findUserVoteForToday(userName) != null){
            successMsg = String.format(USER_CHANGE_VOTE_SUCCESSFULLY_MESSAGE, restaurantName);
        } else {
            successMsg = String.format(USER_VOTED_SUCCESSFULLY_MESSAGE, restaurantName);
        }
        userService.voteForRestaurant(userName, restaurantName);
        logDebug(successMsg);
        response = createResponse(SUCCESSFUL_CODE, successMsg);
        return response;
    }

    private MostVotedRestaurantJson prepareResult(Map<Restaurant, Long> found){
        MostVotedRestaurantJson result = new MostVotedRestaurantJson();
        if (!found.isEmpty()){
            Map.Entry<Restaurant, Long> entry = found.entrySet().iterator().next();
            result.setRestaurantName(entry.getKey().getName());
            result.setVoteNumber(entry.getValue());
        } else {
            result.setMessage(NO_VOTES_EXIST_MESSAGE);
        }
        return result;
    }
}
