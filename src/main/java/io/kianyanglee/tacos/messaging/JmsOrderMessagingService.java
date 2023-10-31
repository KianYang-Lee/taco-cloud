package io.kianyanglee.tacos.messaging;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import io.kianyanglee.tacos.domain.TacoOrder;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;

@Service
public class JmsOrderMessagingService implements OrderMessagingService {

    private JmsTemplate jmsTemplate;

    private Destination orderQueue;

    public JmsOrderMessagingService(JmsTemplate jmsTemplate, Destination orderQueue) {
        this.jmsTemplate = jmsTemplate;
        this.orderQueue = orderQueue;
    }

    /**
     * Implementation using anonymous inner class
     */
    // @Override
    // public void sendOrder(TacoOrder order) {
    // jmsTemplate.send(new MessageCreator() {
    // @Override
    // public Message createMessage(Session session) throws JMSException {
    // return session.createObjectMessage(order);
    // }
    // });
    // }

    /**
     * Implementation using lambda for functional interface
     * 
     * Sends to default location.
     * 
     * @param order
     * @return
     */
    // @Override
    // public void sendOrder(TacoOrder order) {
    // jmsTemplate.send((session) -> session.createObjectMessage(order));
    // }

    /**
     * Sends to a defined location
     */
    // @Override
    // public void sendOrder(TacoOrder order) {
    // jmsTemplate.send(
    // orderQueue, // Alternatively can just specify location using String
    // (session) -> session.createObjectMessage(order));
    // }

    /**
     * Convert object into Message first before sending using MessageConverter
     */
    // public void sendOrder(TacoOrder order) {
    // jmsTemplate.convertAndSend("tacocloud.order.queue", order);
    // }

    /**
     * Add a custom header for postprocessing message using lambda
     */
    // public void sendOrder(TacoOrder order) {
    // jmsTemplate.send("tacocloud.order.queue", session -> {
    // Message message = session.createObjectMessage(order);
    // message.setStringProperty("X_ORDER_SOURCE", "WEB");
    // return message;
    // });
    // }

    public void sendOrder(TacoOrder order) {
        jmsTemplate.convertAndSend("tacocloud.order.queue", order, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

}
