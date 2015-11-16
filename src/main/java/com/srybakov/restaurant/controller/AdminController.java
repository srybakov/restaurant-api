package com.srybakov.restaurant.controller;

import com.srybakov.restaurant.ApplicationConstants;
import com.srybakov.restaurant.domain.webmodel.CreateUserJson;
import com.srybakov.restaurant.domain.webmodel.MenuJson;
import com.srybakov.restaurant.domain.webmodel.ResponseJson;
import com.srybakov.restaurant.domain.webmodel.RestaurantJson;
import com.srybakov.restaurant.exception.RestaurantAlreadyExistsException;
import com.srybakov.restaurant.exception.RestaurantDoesNotExistException;
import com.srybakov.restaurant.exception.RoleDoesNotExsistException;
import com.srybakov.restaurant.exception.UserAlreadyExistException;
import com.srybakov.restaurant.exception.UserNameMandatoryException;
import com.srybakov.restaurant.exception.UserPasswordMandatoryException;
import com.srybakov.restaurant.exception.UserRoleMandatoryException;
import com.srybakov.restaurant.service.RestaurantService;
import com.srybakov.restaurant.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.srybakov.restaurant.ApplicationConstants.*;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Controller
@RequestMapping(value = "/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController extends BaseController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/createRestaurant", method = RequestMethod.POST)
    public @ResponseBody ResponseJson createRestaurantWS(@RequestBody RestaurantJson createRestaurantRequest) {
        ResponseJson response;
        if (StringUtils.isEmpty(createRestaurantRequest.getRestaurantName())){
            response = createResponse(EMPTY_RESTAURANT_NAME_CODE, RESTAURANT_NAME_MANDATORY_MESSAGE);
        } else {
            logDebug(CREATE_RESTAURANT_REQUESTED_MESSAGE, createRestaurantRequest.getRestaurantName());
            response = createRestaurant(createRestaurantRequest);
        }
        return response;
    }

    @RequestMapping(value = "/inputCurrentMenuForRestaurant", method = RequestMethod.POST)
    public @ResponseBody ResponseJson inputMenuWS(@RequestBody MenuJson menuJson) {
        ResponseJson response;
        if (StringUtils.isEmpty(menuJson.getRestaurantName())){
            response = createResponse(EMPTY_RESTAURANT_NAME_CODE, RESTAURANT_NAME_MANDATORY_MESSAGE);
        } else {
            logDebug(INPUT_MENU_REQUESTED_MESSAGE, menuJson.getRestaurantName(), menuJson.getMenu().toString());
            response = inputCurrentMenu(menuJson);
        }
        return response;
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody ResponseJson createUserWS(@RequestBody CreateUserJson createUserJson){
        ResponseJson response;
        try {
            logDebug(CREATE_USER_REQUEST_RECEIVED, createUserJson.getUserName(), createUserJson.getRoles());
            userService.createUser(createUserJson.getUserName(), createUserJson.getPassword(),
                    createUserJson.getRoles());
            response = new ResponseJson();
            response.setCode(SUCCESSFUL_CODE);
            response.setMessage(USER_CREATE_REQUEST_SUCCESSFUL_MESSAGE);
        } catch (UserRoleMandatoryException e) {
            response = createResponse(USER_ROLE_IS_MANDATORY_CODE, USER_ROLE_IS_MANDATORY_MESSAGE);
        } catch (UserPasswordMandatoryException e) {
            response = createResponse(PASSWORD_IS_MANDATORY_CODE, PASSWORD_IS_MANDATORY_MESSAGE);
        } catch (UserNameMandatoryException e) {
            response = createResponse(USER_NAME_IS_MANDATORY_CODE, USER_NAME_IS_MANDATORY_MESSAGE);
        } catch (RoleDoesNotExsistException e) {
            String message = String.format(SPECIFIED_ROLE_DOES_NOT_EXISTS_MESSAGE, e.getMessage());
            response = createResponse(SPECIFIED_ROLE_DOES_NOT_EXISTS_CODE, message);
        } catch (UserAlreadyExistException e) {
            response = createResponse(USER_ALREADY_EXISTS_CODE, USER_ALREADY_EXISTS_MESSAGE);
        }
        return response;
    }

    private ResponseJson inputCurrentMenu(MenuJson inputMenuRequest){
        ResponseJson response;
        try {
            restaurantService.inputCurrentMenu(inputMenuRequest.getRestaurantName(), inputMenuRequest.getMenu());
            String successMsg = String.format(INPUT_MENU_SUCCESSFUL_MESSAGE, inputMenuRequest.getRestaurantName(),
                    inputMenuRequest.getMenu().toString());
            response = createResponse(SUCCESSFUL_CODE, successMsg);
            logDebug(successMsg);
        } catch (RestaurantDoesNotExistException rdne){
            response = createResponse(RESTAURANT_DOES_NOT_EXIST_CODE, rdne.getMessage());
            LOG.error(rdne.getMessage());
        } catch (Exception e){
            String msg = String.format(INPUT_MENU_ERROR_MESSAGE, inputMenuRequest.getRestaurantName(),
                    inputMenuRequest.getMenu().toString(), e.getMessage());
            response = createResponse(INTERNAL_ERROR_CODE, msg);
            LOG.error(msg);
        }
        return response;
    }

    private ResponseJson createRestaurant(RestaurantJson createRestaurantRequest){
        ResponseJson response;
        try {
            restaurantService.createRestaurant(createRestaurantRequest.getRestaurantName());
            response = createResponse(SUCCESSFUL_CODE, String.format(ApplicationConstants
                    .RESTAURANT_CREATED_SUCCESSFUL_MESSAGE, createRestaurantRequest.getRestaurantName()));
            logDebug(CREATE_RESTAURANT_SUCCESSFUL_MESSAGE, createRestaurantRequest.getRestaurantName());
        } catch (RestaurantAlreadyExistsException rae){
            response = createResponse(RESTAURANT_ALREADY_EXISTS_CODE, rae.getMessage());
            LOG.error(rae.getMessage());
        } catch (Exception e){
            String msg = String.format(CREATE_RESTAURANT_ERROR_MESSAGE, createRestaurantRequest.getRestaurantName(),
                    e.getMessage());
            response = createResponse(INTERNAL_ERROR_CODE, msg);
            LOG.error(msg);
        }
        return response;
    }

}
