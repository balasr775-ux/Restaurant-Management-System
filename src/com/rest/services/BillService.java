package com.rest.services;

import com.rest.models.Bill;
import com.rest.models.Order;
import com.rest.repository.Billrepository;
import java.util.List;
import java.util.Optional;

public class BillService {
	private final Billrepository billRepository;
	private final OrderService orderService;
	private int nextBillId = 1;

	public BillService(OrderService orderService) {
		this.billRepository = new Billrepository();
		this.orderService = orderService;
	}

	public Bill createBill(int orderId, double taxRate, double tip) {
		Order order = orderService.getOrderById(orderId)
				.orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
		Bill bill = new Bill(nextBillId++, order, taxRate, tip);
		billRepository.save(bill);
		return bill;
	}

	public List<Bill> getAllBills() {
		return billRepository.findAll();
	}

	public Optional<Bill> getBillById(int id) {
		return billRepository.findById(id);
	}
}
