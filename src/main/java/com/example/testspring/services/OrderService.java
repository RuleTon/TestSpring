package com.example.testspring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.testspring.model.Order;
import com.example.testspring.model.User;
import com.example.testspring.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createFromUserCart(User user, String address) {
        Order order = new Order(null, user, address); // todo NOT NULL
        order = orderRepository.save(order);
        return order;
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public List<Order> findAllOrdersByOwnerName(String username) {
        return orderRepository.findAllByOwnerUsername(username);
    }
}
