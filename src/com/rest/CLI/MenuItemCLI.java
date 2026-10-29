package com.rest.CLI;

import com.rest.models.MenuItem;
import com.rest.services.MenuItemService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MenuItemCLI {
	private final MenuItemService menuItemService;
	private final Scanner scanner;

	public MenuItemCLI(MenuItemService menuItemService, Scanner scanner) {
		this.menuItemService = menuItemService;
		this.scanner = scanner;
	}

	public void start() {
		boolean running = true;
		while (running) {
			System.out.println("\n=== MENU ITEMS ===");
			System.out.println("1. List menu items");
			System.out.println("2. Add menu item");
			System.out.println("3. Find menu item by ID");
			System.out.println("0. Back");

			switch (readInt("Choose an option: ")) {
				case 1 -> listMenuItems();
				case 2 -> addMenuItem();
				case 3 -> findMenuItem();
				case 0 -> running = false;
				default -> System.out.println("Invalid choice.");
			}
		}
	}

	private void listMenuItems() {
		List<MenuItem> menuItems = menuItemService.getAllMenuItems();
		if (menuItems.isEmpty()) {
			System.out.println("No menu items found.");
			return;
		}
		menuItems.forEach(menuItem -> System.out.println(menuItem.toString()));
	}

	private void addMenuItem() {
		System.out.print("Name: ");
		String name = scanner.nextLine().trim();
		System.out.print("Category: ");
		String category = scanner.nextLine().trim();
		double price = readDouble("Price: ");

		try {
			System.out.println("Added " + menuItemService.addMenuItem(name, price, category));
		} catch (IllegalArgumentException exception) {
			System.out.println(exception.getMessage());
		}
	}

	private void findMenuItem() {
		int id = readInt("Menu item ID: ");
		Optional<MenuItem> menuItem = menuItemService.getMenuItemById(id);
		System.out.println(menuItem.map(Object::toString).orElse("Menu item not found."));
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

