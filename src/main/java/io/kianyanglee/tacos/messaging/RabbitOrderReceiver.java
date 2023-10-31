package io.kianyanglee.tacos.messaging;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.domain.TacoOrder;

@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbitTemplate;
    private MessageConverter converter;

    public RabbitOrderReceiver(RabbitTemplate rabbitTemplate, MessageConverter converter) {
        this.rabbitTemplate = rabbitTemplate;
        this.converter = converter;
    }

    public TacoOrder receiveOrder() {
        Message message = rabbitTemplate.receive("tacocloud.order");
        return message != null ? (TacoOrder) converter.fromMessage(message) : null;
    }
}
