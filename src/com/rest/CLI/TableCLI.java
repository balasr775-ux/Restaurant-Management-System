
package com.rest.CLI;

import com.rest.models.Table;
import com.rest.services.TableService;
import java.util.*;

public class TableCLI {

    private final TableService service;
    private final Scanner sc;

    public TableCLI(TableService service, Scanner sc) {
        this.service = service;
        this.sc = sc;
    }

    public void start() {
        System.out.println(" ===RESTAURANT TABLE MANAGEMENT ==");
        System.out.println("1.View All Tables");
        System.out.println("0.Exit");

        System.out.print("enter choice: ");

        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> {
                List<Table> tables = service.getAllTables();
                System.out.println(" ===All Tables ===");
                for (Table table : tables) {
                    System.out.println(table);
                }
            }
            case 0 -> System.out.println("Thank You");
            default -> System.out.println("Invalid Choice");
        }
    }
}
