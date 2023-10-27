package io.kianyanglee.tacos.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import io.kianyanglee.tacos.domain.TacoOrder;
import io.kianyanglee.tacos.domain.User;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
