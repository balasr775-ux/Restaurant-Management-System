package com.rest.CLI;

import com.rest.models.Payment;
import com.rest.payment.CashPayment;
import com.rest.services.PaymentService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PaymentCLI {
    private final PaymentService paymentService;
    private final Scanner scanner;

    public PaymentCLI() {
        this(new PaymentService(), new Scanner(System.in));
    }

    public PaymentCLI(PaymentService paymentService, Scanner scanner) {
        this.paymentService = paymentService;
        this.scanner = scanner;
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== PAYMENT MANAGEMENT ===");
            System.out.println("1. Make a payment");
            System.out.println("2. View all payments");
            System.out.println("3. View payment by ID");
            System.out.println("0. Back");

            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1 -> makePayment();
                case 2 -> viewAllPayments();
                case 3 -> viewPaymentById();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void makePayment() {
        System.out.print("Amount: ");
        try {
            double amount = Double.parseDouble(scanner.nextLine().trim());
            Payment payment = paymentService.processPayment(amount, new CashPayment());
            System.out.println("Payment recorded: " + payment);
        } catch (NumberFormatException exception) {
            System.out.println("Enter a valid amount.");
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void viewAllPayments() {
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }
        for (Payment payment : payments) {
            System.out.println(payment);
        }
    }

    private void viewPaymentById() {
        int id = readInt("Payment ID: ");
        Optional<Payment> payment = paymentService.getPaymentById(id);
        if (payment.isEmpty()) {
            System.out.println("Payment not found.");
            return;
        }
        System.out.println(payment.get());
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
}