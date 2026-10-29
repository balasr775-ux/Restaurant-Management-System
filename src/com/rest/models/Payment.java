package com.rest.models;

public class Payment {
     private int id;
     private double amount;
     private String Method;
     private String Status;  

     public Payment(int id, double amount, String Method, String Status) {
         this.id = id;
         this.amount = amount;
         this.Method = Method;
         this.Status = Status;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public double getAmount() {
         return amount;
     }

     public void setAmount(double amount) {
         this.amount = amount;
     }
     public String getMethod() {
         return Method;
     }
     public void setMethod(String Method) {
         this.Method = Method;
     }
     public String getStatus() {
         return Status;
     }
     public void setStatus(String Status) {
         this.Status = Status;
     }
     @Override
     public String toString() {
            return "Payment [id=" + id + ", amount=" + amount + ", Method=" + Method + ",  Status=" + Status + "]";

     }
 }
