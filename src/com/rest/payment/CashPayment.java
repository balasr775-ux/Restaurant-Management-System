package com.rest.payment;

public class CashPayment implements Paymentmethod {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using cash.");
    }

    @Override
    public String getMethod() {
        return "CASH";
    }

    @Override 
    public String toString() {
        return "CASH";
    }
    
}
