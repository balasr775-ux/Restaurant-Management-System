package com.rest.repository;

import com.rest.models.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Orderrepository {

    private final List<Order> orders = new ArrayList<>();
    private int nextId = 1;

    public Order createOrder(int tableId) {
        Order order = new Order(nextId++, tableId);
        orders.add(order);
        return order;
    }

    public Optional<Order> findById(int id) {
        return orders.stream()
                .filter(order -> order.getId() == id)
                .findFirst();
    }

    public List<Order> findByTableId(int tableId) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getTableId() == tableId) {
                result.add(order);
            }
        }
        return result;
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }
}