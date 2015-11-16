package com.srybakov.restaurant.service.impl;

import com.srybakov.restaurant.ApplicationConstants;
import com.srybakov.restaurant.domain.model.Dish;
import com.srybakov.restaurant.domain.model.Menu;
import com.srybakov.restaurant.domain.model.Restaurant;
import com.srybakov.restaurant.domain.repository.DishRepository;
import com.srybakov.restaurant.domain.repository.MenuRepository;
import com.srybakov.restaurant.domain.repository.RestaurantRepository;
import com.srybakov.restaurant.domain.repository.VoteRepository;
import com.srybakov.restaurant.exception.RestaurantAlreadyExistsException;
import com.srybakov.restaurant.exception.RestaurantDoesNotExistException;
import com.srybakov.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private static final Pageable FIRST_ONE_RECORD = new PageRequest(0, 1);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    @Transactional(readOnly = false)
    public void createRestaurant(String name) throws RestaurantAlreadyExistsException {
        Restaurant restaurant;
        restaurant = findByName(name);
        if (restaurant == null){
            restaurant = new Restaurant();
            restaurant.setName(name);
            restaurantRepository.save(restaurant);
        } else {
            throw new RestaurantAlreadyExistsException(String.format(
                    ApplicationConstants.RESTAURANT_ALREADY_EXISTS_MESSAGE, name));
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void inputCurrentMenu(String restaurantName, Map<String, Double> menu)
        throws RestaurantDoesNotExistException {

        Restaurant restaurant = findByName(restaurantName);
        if (restaurant != null){
            Menu currentMenu = menuRepository.findCurrentMenuForRestaurant(restaurantName);
            if (currentMenu == null){
                currentMenu = createMenu(restaurant);
            } else {
                clearMenu(currentMenu.getDishList());
                currentMenu.getDishList().clear();
            }
            addMenuItems(currentMenu, menu);
        } else {
            throw new RestaurantDoesNotExistException(String.format(
                    ApplicationConstants.RESTAURANT_DOES_NOT_EXIST_MESSAGE, restaurantName));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Restaurant findByName(String name) {
        return restaurantRepository.findByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Restaurant, Long> findMostVoted() {
        List<Map<String, Object>> mostVoted = voteRepository.findMostVoted(FIRST_ONE_RECORD);
        return getFilledResult(mostVoted);
    }

    @Override
    @Transactional(readOnly = true)
    public Map<Restaurant, Long> findMostVotedForToday() {
        List<Map<String, Object>> mostVoted = voteRepository.findMostVotedForToday(FIRST_ONE_RECORD);
        return getFilledResult(mostVoted);
    }

    private Map<Restaurant, Long> getFilledResult(List<Map<String, Object>> mostVoted){
        Map<Restaurant, Long> result = new HashMap<>();
        if (!mostVoted.isEmpty()){
            Restaurant restaurant = null;
            Long votes = 0L;
            for (Object entry : mostVoted.iterator().next().values()){
                if (entry instanceof Restaurant){
                    restaurant = (Restaurant) entry;
                } else {
                    votes = (Long) entry;
                }
            }
            result.put(restaurant, votes);
        }
        return result;
    }

    private void clearMenu(List<Dish> dishList){
        for (Dish dish : dishList){
            dishRepository.delete(dish);
        }
    }

    private void addMenuItems(Menu menu, Map<String, Double> sourceMenu){
        for (Map.Entry<String, Double> menuItem : sourceMenu.entrySet()) {
            Dish dish = new Dish();
            dish.setName(menuItem.getKey());
            dish.setCost(menuItem.getValue());
            dish.setMenu(menu);
            dishRepository.save(dish);
        }
    }

    private Menu createMenu(Restaurant restaurant){
        Menu currentMenu = new Menu();
        currentMenu.setActuallyForDate(new Date());
        currentMenu.setRestaurant(restaurant);
        menuRepository.save(currentMenu);
        return currentMenu;
    }
}
