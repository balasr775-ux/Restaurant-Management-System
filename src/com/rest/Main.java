package com.rest;

import com.rest.CLI.BillCLI;
import com.rest.CLI.MenuItemCLI;
import com.rest.CLI.OrderCLI;
import com.rest.CLI.TableCLI;
import com.rest.services.BillService;
import com.rest.services.MenuItemService;
import com.rest.services.OrderService;
import com.rest.services.TableService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TableService tableService = new TableService();
        OrderService orderService = new OrderService(tableService);
        MenuItemCLI menuItemCLI = new MenuItemCLI(new MenuItemService(), scanner);
        BillCLI billCLI = new BillCLI(new BillService(orderService), scanner);
        TableCLI tableCLI = new TableCLI(tableService, scanner);
        OrderCLI orderCLI = new OrderCLI(orderService, scanner);

        boolean running = true;
        while (running) {
            System.out.println("\n=== RESTAURANT MANAGEMENT ===");
            System.out.println("1. Table management");
            System.out.println("2. Order management");
            System.out.println("3. Menu items");
            System.out.println("4. Bills");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException exception) {
                System.out.println("Enter a whole number.");
                continue;
            }

            switch (choice) {
                case 1 -> tableCLI.start();
                case 2 -> orderCLI.start();
                case 3 -> menuItemCLI.start();
                case 4 -> billCLI.start();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye.");
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}