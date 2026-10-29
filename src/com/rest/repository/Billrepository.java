package com.rest.repository;

import com.rest.models.Bill;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Billrepository {
	private final List<Bill> bills = new ArrayList<>();

	public void save(Bill bill) {
		bills.add(bill);
	}

	public List<Bill> findAll() {
		return new ArrayList<>(bills);
	}

	public Optional<Bill> findById(int id) {
		return bills.stream()
				.filter(bill -> bill.getBillId() == id)
				.findFirst();
	}
}