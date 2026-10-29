package com.rest.models;

public class MenuItem {
	private final int id;
	private final String name;
	private final double price;
	private final String category;

	public MenuItem(int id, String name, double price, String category) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		return String.format("[%d] %s | %s | $%.2f", id, name, category, price);
	}
}

