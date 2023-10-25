package io.kianyanglee.tacos.repositories;

import org.springframework.data.repository.CrudRepository;

import io.kianyanglee.tacos.domain.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
