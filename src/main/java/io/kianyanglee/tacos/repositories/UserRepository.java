package io.kianyanglee.tacos.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import io.kianyanglee.tacos.domain.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
