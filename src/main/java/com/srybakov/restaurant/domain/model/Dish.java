package com.srybakov.restaurant.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Entity
@Table(name = "dishes")
public class Dish extends BaseEntity<Long> {

    private static final int DISH_NAME_LENGTH = 128;

    @Column(name = "name", length = DISH_NAME_LENGTH, nullable = false)
    private String name;

    @Column(name = "cost", nullable = false, columnDefinition = "Decimal(10, 2)")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
