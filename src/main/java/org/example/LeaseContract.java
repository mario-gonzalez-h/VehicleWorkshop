package org.example;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, double expectedEndingValue, double leaseFee) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public void calculateTotalPrice() {
        // Calculation specific to lease contracts
        this.expectedEndingValue = getTotalPrice() * 0.5; // Calculate expected ending value as 50% of the original price
        this.leaseFee = getTotalPrice() * 0.07; // Calculate lease fee as 7% of the original price
        double totalPrice = this.expectedEndingValue + this.leaseFee;
        setTotalPrice(totalPrice);
    }



    @Override
    public void calculateMonthlyPayment() {
        // Principal is the total price of the contract
        double principal = getTotalPrice();

        // Interest rate and term for lease contracts
        double interestRate = 0.04; // Example interest rate for leases
        int months = 36;             // Example term for lease in months

        // Monthly interest rate
        double monthlyRate = interestRate / 12;

        // Monthly payment calculation
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }
}
