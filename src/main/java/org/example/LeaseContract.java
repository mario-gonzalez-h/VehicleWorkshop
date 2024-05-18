package org.example;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment) {
        super(dateOfContract, customerName, customerEmail, vehicleSold, totalPrice, monthlyPayment);
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
        double vehiclePrice = Vehicle.getPrice();  // Use the vehicle price from the Vehicle class
        this.expectedEndingValue = vehiclePrice * 0.5; // Calculate expected ending value as 50% of the original price
        this.leaseFee = vehiclePrice * 0.07; // Calculate lease fee as 7% of the original price
        double totalPrice = this.expectedEndingValue + this.leaseFee;
        setTotalPrice(totalPrice);
    }

    @Override
    public void calculateMonthlyPayment() {
        double principal = getTotalPrice();
        double interestRate = 0.04; // Example interest rate for leases
        int months = 36;            // Example term for lease in months
        double monthlyRate = interestRate / 12;
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }
}
