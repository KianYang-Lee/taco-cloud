package io.kianyanglee.tacos.messaging;

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

    public void receiveOrder(TacoOrder order) {
        log.info("Listener received order : {}", order);
    }
    
}
