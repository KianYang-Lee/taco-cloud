package io.kianyanglee.tacos.messaging;

import io.kianyanglee.tacos.domain.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
