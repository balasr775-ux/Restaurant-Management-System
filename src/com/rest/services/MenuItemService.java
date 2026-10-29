package com.rest.services;

import com.rest.models.MenuItem;
import com.rest.repository.MenuItemrepository;
import java.util.List;
import java.util.Optional;

public class MenuItemService {
	private final MenuItemrepository menuItemRepository;

	public MenuItemService() {
		this.menuItemRepository = new MenuItemrepository();
	}

	public MenuItem addMenuItem(String name, double price, String category) {
		if (name == null || name.isBlank()) {
			throw new IllegalArgumentException("Menu item name is required.");
		}
		if (!Double.isFinite(price) || price <= 0) {
			throw new IllegalArgumentException("Price must be greater than zero.");
		}
		if (category == null || category.isBlank()) {
			throw new IllegalArgumentException("Category is required.");
		}

		return menuItemRepository.save(name.trim(), price, category.trim());
	}

	public List<MenuItem> getAllMenuItems() {
		return menuItemRepository.findAll();
	}

	public Optional<MenuItem> getMenuItemById(int id) {
		return menuItemRepository.findById(id);
	}
}
