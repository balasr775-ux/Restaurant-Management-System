package com.rest.models;

import java.time.LocalDateTime;

public class Bill {
	private final int billId;
	private final Order order;
	private final double subTotal;
	private final double taxRate;
	private final double tip;
	private final double grandTotal;
	private final LocalDateTime generatedAt;

	public Bill(int billId, Order order, double taxRate, double tip) {
		if (order == null) {
			throw new IllegalArgumentException("Order is required.");
		}
		if (!Double.isFinite(taxRate) || taxRate < 0) {
			throw new IllegalArgumentException("Tax rate cannot be negative.");
		}
		if (!Double.isFinite(tip) || tip < 0) {
			throw new IllegalArgumentException("Tip cannot be negative.");
		}

		this.billId = billId;
		this.order = order;
		this.taxRate = taxRate;
		this.tip = tip;
		this.subTotal = order.getTotalAmount();
		this.grandTotal = subTotal + (subTotal * taxRate) + tip;
		this.generatedAt = LocalDateTime.now();
	}

	public int getBillId() {
		return billId;
	}

	public Order getOrder() {
		return order;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public double getTip() {
		return tip;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public LocalDateTime getGeneratedAt() {
		return generatedAt;
	}

	@Override
	public String toString() {
		return String.format(
				"Bill #%d | Order #%d | Subtotal: $%.2f | Tax: $%.2f | Tip: $%.2f | Total: $%.2f | %s",
				billId, order.getId(), subTotal, subTotal * taxRate, tip, grandTotal, generatedAt);
	}
}
