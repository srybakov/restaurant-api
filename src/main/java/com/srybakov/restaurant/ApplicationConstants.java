package com.srybakov.restaurant;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public final class ApplicationConstants {

    /* PACKAGES */
    public static final String MODEL_PACKAGE = "com.srybakov.restaurant.domain.model";
    public static final String REPOSITORY_PACKAGE = "com.srybakov.restaurant.domain.repository";

    /* WS RESPONSE CODES */
    public static final int SUCCESSFUL_CODE = 200;
    public static final int INTERNAL_ERROR_CODE = 500;
    public static final int EMPTY_RESTAURANT_NAME_CODE = 501;
    public static final int RESTAURANT_ALREADY_EXISTS_CODE = 502;
    public static final int RESTAURANT_DOES_NOT_EXIST_CODE = 503;
    public static final int USER_NAME_IS_MANDATORY_CODE = 504;
    public static final int PASSWORD_IS_MANDATORY_CODE = 505;
    public static final int USER_ROLE_IS_MANDATORY_CODE = 506;
    public static final int USER_ALREADY_EXISTS_CODE = 507;
    public static final int SPECIFIED_ROLE_DOES_NOT_EXISTS_CODE = 508;
    public static final int USER_CANNOT_VOTE_TODAY_CODE = 509;
    public static final int USER_CANNOT_VOTE_TWICE_FOR_SAME_RESTAURANT_CODE = 510;

    /* MESSAGES */
    public static final String AUTH_FAILED_MESSAGE = "User is not found or has no granted authorities. User: %s";
    public static final String RESTAURANT_CREATED_SUCCESSFUL_MESSAGE = "Restaurant '%s' created successful";
    public static final String RESTAURANT_NAME_MANDATORY_MESSAGE = "Restaurant name should be specified";
    public static final String CREATE_RESTAURANT_REQUESTED_MESSAGE = "Create restaurant request received. Restaurant name '%s'";
    public static final String CREATE_RESTAURANT_SUCCESSFUL_MESSAGE = "Restaurant '%s' created successfully";
    public static final String CREATE_RESTAURANT_ERROR_MESSAGE = "Restaurant '%s' creation failed due to following error: '%s'";
    public static final String INPUT_MENU_REQUESTED_MESSAGE = "Input menu request received. Restaurant name '%s'. Menu: '%s'";
    public static final String INPUT_MENU_ERROR_MESSAGE = "Input menu request failed. Restaurant name '%s'. Menu: '%s'. Error: '%s'";
    public static final String INPUT_MENU_SUCCESSFUL_MESSAGE = "Input menu request successful. Restaurant name '%s'. Menu: '%s'";
    public static final String RESTAURANT_ALREADY_EXISTS_MESSAGE = "Restaurant with name '%s' already exists";
    public static final String RESTAURANT_DOES_NOT_EXIST_MESSAGE = "Restaurant with name '%s' does not exist";
    public static final String NO_VOTES_EXIST_MESSAGE = "Users have not been voted at all";
    public static final String USER_NAME_IS_MANDATORY_MESSAGE = "User name is mandatory for user creation";
    public static final String PASSWORD_IS_MANDATORY_MESSAGE = "Password is mandatory for user creation";
    public static final String USER_ROLE_IS_MANDATORY_MESSAGE = "User role is mandatory for user creation";
    public static final String USER_ALREADY_EXISTS_MESSAGE = "User with such name already exists";
    public static final String SPECIFIED_ROLE_DOES_NOT_EXISTS_MESSAGE = "Wrong role param. '%s'";
    public static final String USER_CREATE_REQUEST_SUCCESSFUL_MESSAGE = "User with specified parameters successfully created";
    public static final String CREATE_USER_REQUEST_RECEIVED = "User name: '%s', roles: '%s'";
    public static final String USER_CANNOT_VOTE_TODAY_MESSAGE = "User cannot vote today already";
    public static final String USER_VOTED_SUCCESSFULLY_MESSAGE = "User has been voted successfully for restaurant '%s'";
    public static final String USER_CHANGE_VOTE_SUCCESSFULLY_MESSAGE = "User has been changed his mind and voted for restaurant '%s'";
    public static final String USER_CANNOT_VOTE_TWICE_FOR_SAME_RESTAURANT_MESSAGE = "User cannot vote for the same restaurant twice a day";

    private ApplicationConstants(){}

}
