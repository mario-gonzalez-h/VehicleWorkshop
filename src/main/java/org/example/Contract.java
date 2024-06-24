//package org.example;
//
//public abstract class Contract {
//    private String dateOfContract;
//    private String customerName;
//    private String customerEmail;
//    private boolean vehicleSold;
//    private double totalPrice;
//    private double monthlyPayment;
//
//    public Contract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment) {
//        this.dateOfContract = dateOfContract;
//        this.customerName = customerName;
//        this.customerEmail = customerEmail;
//        this.vehicleSold = vehicleSold;
//        this.totalPrice = totalPrice;
//        this.monthlyPayment = monthlyPayment;
//    }
//
//    public String getDateOfContract() {
//        return dateOfContract;
//    }
//
//    public void setDateOfContract(String dateOfContract) {
//        this.dateOfContract = dateOfContract;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCustomerEmail() {
//        return customerEmail;
//    }
//
//    public void setCustomerEmail(String customerEmail) {
//        this.customerEmail = customerEmail;
//    }
//
//    public boolean isVehicleSold() {
//        return vehicleSold;
//    }
//
//    public void setVehicleSold(boolean vehicleSold) {
//        this.vehicleSold = vehicleSold;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public double getMonthlyPayment() {
//        return monthlyPayment;
//    }
//
//    public void setMonthlyPayment(double monthlyPayment) {
//        this.monthlyPayment = monthlyPayment;
//    }
//
//    // Abstract methods
//    public abstract void calculateTotalPrice();
//    public abstract void calculateMonthlyPayment();
//}
