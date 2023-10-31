package io.kianyanglee.tacos.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import io.kianyanglee.tacos.domain.TacoOrder;
import jakarta.jms.Destination;

@Configuration
public class MessagingConfig {
    
    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }

    @Bean
    public MappingJackson2MessageConverter messageConverterJms() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
        typeIdMappings.put("order", TacoOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);
        return messageConverter;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverterRabbit() {
        return new Jackson2JsonMessageConverter();
    }
}
