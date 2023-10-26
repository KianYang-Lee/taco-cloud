package io.kianyanglee.tacos.services;

import org.springframework.stereotype.Service;

import io.kianyanglee.tacos.repositories.OrderRepository;

@Service
public class AdminService {

    private OrderRepository orderRepository;

    public AdminService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }
}
