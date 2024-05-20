package org.example;

public class SalesContract extends Contract {
    private double salesTaxAmount; // 5% of the vehicle price
    private double recordingFee;   // Fixed amount of $100
    private double processingFee;  // $295 if vehicle price < $10,000, otherwise $495
    private boolean finance;       // Indicates if the contract is financed

    public SalesContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, boolean finance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        double vehiclePrice = Vehicle.getPrice();  // Use the vehicle price from the Vehicle class
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

    @Override
    public void calculateTotalPrice() {
        double vehiclePrice = Vehicle.getPrice();  // Use the vehicle price from the Vehicle class
        double totalPrice = vehiclePrice + salesTaxAmount + recordingFee + processingFee;

        if (finance) {
            calculateMonthlyPayment(); // Ensure monthly payment is calculated
            double totalInterestPaid = getMonthlyPayment() * getLoanDurationMonths() - vehiclePrice;
            totalPrice += totalInterestPaid;
        }

        setTotalPrice(totalPrice);
    }

    @Override
    public void calculateMonthlyPayment() {
        if (!finance) {
            setMonthlyPayment(0.00);
            return;
        }

        double vehiclePrice = Vehicle.getPrice();  // Use the vehicle price from the Vehicle class
        double interestRate = vehiclePrice >= 10000 ? 0.0425 : 0.0525; // 4.25% if price >= $10,000, otherwise 5.25%
        int months = getLoanDurationMonths(); // 48 months if price >= $10,000, otherwise 24 months
        double monthlyRate = interestRate / 12;
        double principal = vehiclePrice + salesTaxAmount + recordingFee + processingFee;

        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }

    private int getLoanDurationMonths() {
        double vehiclePrice = Vehicle.getPrice();
        return vehiclePrice >= 10000 ? 48 : 24;
    }
}
