package io.kianyanglee.tacos.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.domain.TacoOrder;

@Component
public class JmsOrderReceiver implements OrderReceiver {

    private JmsTemplate jmsTemplate;
    // private MessageConverter converter;

    public JmsOrderReceiver(JmsTemplate jmsTemplate
    // MessageConverter converter
    ) {
        this.jmsTemplate = jmsTemplate;
        // this.converter = converter;
    }

    /**
     * Receiving raw Message and extracting payload.
     */
    // @Override
    // public TacoOrder receiveOrder() throws JMSException {
    // Message message = jmsTemplate.receive("tacocloud.order.queue");
    // return (TacoOrder) converter.fromMessage(message);
    // }

    /**
     * Straightaway extract payload.
     */
    public TacoOrder receiveOrder() {
        return (TacoOrder) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
    }

}
