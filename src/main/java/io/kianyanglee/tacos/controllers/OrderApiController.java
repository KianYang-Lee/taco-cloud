package io.kianyanglee.tacos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.kianyanglee.tacos.domain.TacoOrder;
import io.kianyanglee.tacos.messaging.OrderMessagingService;
import io.kianyanglee.tacos.repositories.OrderRepository;

@RestController
@RequestMapping(path = "/api/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class OrderApiController {

    private OrderRepository orderRepository;
    private OrderMessagingService messageService;

    public OrderApiController(OrderRepository orderRepository, OrderMessagingService messagingService) {
        this.orderRepository = orderRepository;
        this.messageService = messagingService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order) {
        messageService.sendOrder(order);
        return orderRepository.save(order);
    }
}
