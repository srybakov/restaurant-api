package com.srybakov.restaurant.domain.repository;

import com.srybakov.restaurant.domain.model.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

}
