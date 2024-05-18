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
        // Use the vehicle price from the Vehicle class
        double vehiclePrice = Vehicle.getPrice();  
        // Calculate expected ending value as 50% of the original price
        this.expectedEndingValue = vehiclePrice * 0.5; 
        // Calculate lease fee as 7% of the original price
        this.leaseFee = vehiclePrice * 0.07; 
        double totalPrice = this.expectedEndingValue + this.leaseFee;
        setTotalPrice(totalPrice);
    }

    @Override
    public void calculateMonthlyPayment() {
        double principal = getTotalPrice();
        double interestRate = 0.04; 
        int months = 36;            
        double monthlyRate = interestRate / 12;
        double monthlyPayment = (principal * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
        setMonthlyPayment(monthlyPayment);
    }
}
