package com.rest.services;

import com.rest.models.Payment;
import com.rest.payment.Paymentmethod;
import com.rest.repository.Paymentrepository;
import java.util.List;
import java.util.Optional;

public class PaymentService {
    
    private final Paymentrepository paymentRepository;
    private int nextPaymentId = 1;
    
    public PaymentService() {
        paymentRepository = new Paymentrepository();
    }
    public Payment processPayment(double amount, Paymentmethod method) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        if (method == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }

        method.pay(amount);
        Payment payment = new Payment(nextPaymentId++, amount, method.getMethod(), "PAID");
        paymentRepository.save(payment);
        return payment;
    }
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    public Optional<Payment> getPaymentById(int id) {
        return paymentRepository.findById(id);
    }
}
