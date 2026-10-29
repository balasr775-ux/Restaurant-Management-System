package com.rest.CLI;

import com.rest.models.Bill;
import com.rest.services.BillService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BillCLI {
	private final BillService billService;
	private final Scanner scanner;

	public BillCLI(BillService billService, Scanner scanner) {
		this.billService = billService;
		this.scanner = scanner;
	}

	public void start() {
		boolean running = true;
		while (running) {
			System.out.println("\n=== BILLS ===");
			System.out.println("1. Generate bill for order");
			System.out.println("2. List bills");
			System.out.println("3. Find bill by ID");
			System.out.println("0. Back");

			switch (readInt("Choose an option: ")) {
				case 1 -> createBill();
				case 2 -> listBills();
				case 3 -> findBill();
				case 0 -> running = false;
				default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void createBill() {
		int orderId = readInt("Order ID: ");
		double taxPercent = readDouble("Tax percent (for example, 8.25): ");
		double tip = readDouble("Tip amount: $");
		try {
			Bill bill = billService.createBill(orderId, taxPercent / 100.0, tip);
			System.out.println(bill);
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void listBills() {
		List<Bill> bills = billService.getAllBills();
		if (bills.isEmpty()) {
			System.out.println("No bills found.");
			return;
		}
		bills.forEach(bill -> System.out.println(bill.toString()));
	}

	private void findBill() {
		int id = readInt("Bill ID: ");
		Optional<Bill> bill = billService.getBillById(id);
		System.out.println(bill.map(Object::toString).orElse("Bill not found."));
	}

	private int readInt(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException exception) {
				System.out.println("Enter a whole number.");
			}
		}
	}

	private double readDouble(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Double.parseDouble(scanner.nextLine().trim());
			} catch (NumberFormatException exception) {
				System.out.println("Enter a valid number.");
			}
		}
	}
}
