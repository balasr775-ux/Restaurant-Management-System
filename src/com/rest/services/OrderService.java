package com.rest.services;

import com.rest.models.Order;
import com.rest.models.Table;
import com.rest.repository.Orderrepository;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final Orderrepository orderRepo;
    private final TableService tableService;

    public OrderService(TableService tableService) {
        this.orderRepo = new Orderrepository();
        this.tableService = tableService;
    }

    public Order placeOrder(int tableId) {
        Optional<Table> tableOpt = tableService.getAllTables().stream()
                .filter(t -> t.getid() == tableId)
                .findFirst();

        if (tableOpt.isEmpty()) {
            throw new IllegalArgumentException("Table with ID " + tableId + " does not exist.");
        }

        Table table = tableOpt.get();
        table.occupytable();

        return orderRepo.createOrder(tableId);
    }

    public void addItemToOrder(int orderId, String item, double price) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.addItem(item, price);
    }

    public void updateOrderStatus(int orderId, Order.OrderStatus status) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
        order.setStatus(status);

        if (status == Order.OrderStatus.PAID || status == Order.OrderStatus.CANCELLED) {
            tableService.getAllTables().stream()
                    .filter(t -> t.getid() == order.getTableId())
                    .findFirst()
                    .ifPresent(Table::freetable);
        }
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return orderRepo.findById(id);
    }
}