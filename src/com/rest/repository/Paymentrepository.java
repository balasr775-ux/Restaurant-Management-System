package com.rest.repository;

import com.rest.models.Payment;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Paymentrepository {
    private final List<Payment> payments = new ArrayList<>();

    public void save(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }

    public Optional<Payment> findById(int id) {
        return payments.stream()
                .filter(payment -> payment.getId() == id)
                .findFirst();
    }
}
