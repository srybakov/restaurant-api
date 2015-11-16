package com.srybakov.restaurant.domain.repository;

import com.srybakov.restaurant.domain.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    @Query("select m from Menu m where m.restaurant.name = :restaurantName and m.actuallyForDate = CURRENT_DATE")
    Menu findCurrentMenuForRestaurant(@Param(value = "restaurantName") String restaurantName);
}
