package com.srybakov.restaurant.domain.webmodel;

import java.util.Map;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
public class MenuJson extends RestaurantJson {

    public MenuJson() {
    }

    private Map<String, Double> menu;

    public Map<String, Double> getMenu() {
        return menu;
    }

    public void setMenu(Map<String, Double> menu) {
        this.menu = menu;
    }

}
