package com.srybakov.restaurant.domain.repository;

import com.srybakov.restaurant.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:sarybako@gmail.com">Sergey Rybakov</a>
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

}