package org.example;

public class SalesContract extends Contract {
    private double salesTaxAmount; 
    private double recordingFee;  
    private double processingFee; 
    private boolean finance;  

    public SalesContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        double vehiclePrice = Vehicle.getPrice();  
        this.salesTaxAmount = vehiclePrice * 0.05;
        this.recordingFee = 100.00;                
        this.processingFee = vehiclePrice < 10000 ? 295.00 : 495.00;
        this.finance = finance;
    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public void calculateTotalPrice() {
        double vehiclePrice = Vehicle.getPrice(); 
        double totalPrice = vehiclePrice + salesTaxAmount + recordingFee + processingFee;
        setTotalPrice(totalPrice);
    }

    @Override
    public void calculateMonthlyPayment() {
        if (!finance) {
            setMonthlyPayment(0.00);
            return;
        }

        double principal = getTotalPrice();
        double vehiclePrice = Vehicle.getPrice(); 
        double interestRate = vehiclePrice >= 10000 ? 0.0425 : 0.0525; // 4.25% if price >= $10,000, otherwise 5.25%
        int months = vehiclePrice >= 10000 ? 48 : 24; // 48 months if price >= $10,000, otherwise 24 months
        double monthlyRate = interestRate / 12;
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }
}
