package org.example;

public class SalesContract extends Contract {
    private double salesTaxAmount; // 5% of the vehicle price
    private double recordingFee;   // Fixed amount of $100
    private double processingFee;  // $295 if vehicle price < $10,000, otherwise $495
    private boolean finance;       // Indicates if the contract is financed
    private double vehiclePrice;   // Price of the vehicle

    public SalesContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, double vehiclePrice, boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.vehiclePrice = vehiclePrice;
        this.salesTaxAmount = vehiclePrice * 0.05; // 5% sales tax
        this.recordingFee = 100.00;                // Fixed recording fee
        this.processingFee = vehiclePrice < 10000 ? 295.00 : 495.00; // Processing fee based on vehicle price
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

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
        this.salesTaxAmount = vehiclePrice * 0.05; // Recalculate sales tax
        this.processingFee = vehiclePrice < 10000 ? 295 : 495; // Recalculate processing fee
    }

    @Override
    public void calculateTotalPrice() {
        // Calculate and set the total price
        double totalPrice = vehiclePrice + salesTaxAmount + recordingFee + processingFee;
        setTotalPrice(totalPrice);
    }

    @Override
    public void calculateMonthlyPayment() {
        if (!finance) {
            setMonthlyPayment(0.00);
            return;
        }

        // Principal is the total price of the contract
        double principal = getTotalPrice();

        // Interest rate and loan term based on vehicle price
        double interestRate = vehiclePrice >= 10000 ? 0.0425 : 0.0525; // 4.25% if price >= $10,000, otherwise 5.25%
        int months = vehiclePrice >= 10000 ? 48 : 24; // 48 months if price >= $10,000, otherwise 24 months

        // Monthly interest rate
        double monthlyRate = interestRate / 12;

        // Monthly payment calculation
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }

}
