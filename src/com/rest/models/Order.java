package com.rest.models;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public enum OrderStatus {
        CREATED, PREPARING, SERVED, PAID, CANCELLED
    }

    private final int id;
    private final int tableId;
    private final List<String> items;
    private double totalAmount;
    private OrderStatus status;

    public Order(int id, int tableId) {
        this.id = id;
        this.tableId = tableId;
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
        this.status = OrderStatus.CREATED;
    }

    public int getId() {
        return id;
    }

    public int getTableId() {
        return tableId;
    }

    public List<String> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void addItem(String item, double price) {
        this.items.add(item);
        this.totalAmount += price;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order #" + id + " [Table: " + tableId + ", Items: " + items + 
               ", Total: $" + String.format("%.2f", totalAmount) + ", Status: " + status + "]";
    }
}