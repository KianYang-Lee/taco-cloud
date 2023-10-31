package io.kianyanglee.tacos.messaging;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.domain.TacoOrder;
import lombok.extern.slf4j.Slf4j;

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

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder order) {
        log.info("Listener handling order : {}", order);
    }

    /**
     * Consuming metadata
     * @param order
     * @param record
     */
    @KafkaListener(topics="tacocloud.orders.topic")
    public void handle(TacoOrder order, ConsumerRecord<String, TacoOrder> record) {
        log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
        log.info("Listener handling order : {}", order);
    }
}
