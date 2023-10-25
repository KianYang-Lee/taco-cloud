package io.kianyanglee.tacos.repositories;

import io.kianyanglee.tacos.domain.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
}
