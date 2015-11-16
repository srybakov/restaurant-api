package com.srybakov.restaurant.domain.repository;

import com.srybakov.restaurant.domain.model.Restaurant;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Repository
public interface RestaurantRepository extends CrudRepository<Restaurant, Long>{

    Restaurant findByName(String name);

}
