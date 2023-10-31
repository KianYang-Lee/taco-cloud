package io.kianyanglee.tacos.messaging;

import io.kianyanglee.tacos.domain.TacoOrder;
import jakarta.jms.JMSException;

public interface OrderReceiver {
    public TacoOrder receiveOrder() throws JMSException;
}
