package io.kianyanglee.tacos.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;

@Profile("jms-listener")
@Component
@Slf4j
public class OrderListener {

    // Inject view here
    public OrderListener() {}

    // @JmsListener(destination = "tacocloud.order.queue")
    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder order) {
        log.info("Listener received order : {}", order);
    }
    
}
