package org.example;

public class LeaseContract extends Contract{
    private double expectedEndingValue;
    private double leaseFee;
    private double leaseMonthlyPayment;

    public LeaseContract(String dateOfContract, String custumerName, String custumerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, double expectedEndingValue, double leaseFee, double leaseMonthlyPayment) {
        super(dateOfContract, custumerName, custumerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.expectedEndingValue = expectedEndingValue;
        this.leaseFee = leaseFee;
        this.leaseMonthlyPayment = leaseMonthlyPayment;
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

    public double getLeaseMonthlyPayment() {
        return leaseMonthlyPayment;
    }

    public void setLeaseMonthlyPayment(double leaseMonthlyPayment) {
        this.leaseMonthlyPayment = leaseMonthlyPayment;
    }


    @Override
    public void getTotalPrice() {

    }

    @Override
    public void getMonthlyPayment() {

    }
}
