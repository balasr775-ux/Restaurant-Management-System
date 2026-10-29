package com.rest.CLI;

import com.rest.models.Order;
import com.rest.services.OrderService;
import java.util.List;
import java.util.Scanner;

public class OrderCLI {

    private final OrderService orderService;
    private final Scanner sc;

    public OrderCLI(OrderService orderService, Scanner scanner) {
        this.orderService = orderService;
        this.sc = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== RESTAURANT ORDER MANAGEMENT ===");
            System.out.println("1. Create New Order for Table");
            System.out.println("2. Add Item to Order");
            System.out.println("3. View All Orders");
            System.out.println("4. Update Order Status");
            System.out.println("0. Back / Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Table ID: ");
                    int tableId = Integer.parseInt(sc.nextLine());
                    try {
                        Order order = orderService.placeOrder(tableId);
                        System.out.println("Created " + order);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Enter Order ID: ");
                    int orderId = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Item Name: ");
                    String item = sc.nextLine();
                    System.out.print("Enter Item Price: ");
                    double price = Double.parseDouble(sc.nextLine());

                    try {
                        orderService.addItemToOrder(orderId, item, price);
                        System.out.println("Item added successfully.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> {
                    List<Order> orders = orderService.getAllOrders();
                    System.out.println("\n=== All Orders ===");
                    if (orders.isEmpty()) {
                        System.out.println("No orders found.");
                    } else {
                        orders.forEach(System.out::println);
                    }
                }
                case 4 -> {
                    System.out.print("Enter Order ID: ");
                    int orderId = Integer.parseInt(sc.nextLine());
                    System.out.println("Statuses: 1. PREPARING  2. SERVED  3. PAID  4. CANCELLED");
                    System.out.print("Select Status: ");
                    int statusChoice = Integer.parseInt(sc.nextLine());

                    Order.OrderStatus status = switch (statusChoice) {
                        case 1 -> Order.OrderStatus.PREPARING;
                        case 2 -> Order.OrderStatus.SERVED;
                        case 3 -> Order.OrderStatus.PAID;
                        case 4 -> Order.OrderStatus.CANCELLED;
                        default -> null;
                    };

                    if (status != null) {
                        orderService.updateOrderStatus(orderId, status);
                        System.out.println("Order status updated to " + status);
                    } else {
                        System.out.println("Invalid status option.");
                    }
                }
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}