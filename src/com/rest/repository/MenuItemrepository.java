package com.rest.repository;

import com.rest.models.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuItemrepository {
	private final List<MenuItem> menuItems = new ArrayList<>();
	private int nextId = 1;

	public MenuItem save(String name, double price, String category) {
		MenuItem menuItem = new MenuItem(nextId++, name, price, category);
		menuItems.add(menuItem);
		return menuItem;
	}

	public List<MenuItem> findAll() {
		return new ArrayList<>(menuItems);
	}

	public Optional<MenuItem> findById(int id) {
		return menuItems.stream()
				.filter(menuItem -> menuItem.getId() == id)
				.findFirst();
	}
}

