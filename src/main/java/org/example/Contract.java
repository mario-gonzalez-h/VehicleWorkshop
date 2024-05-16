package org.example;

public abstract class Contract {
    private String dateOfContract;
    private String custumerName;
    private String custumerEmail;
    private boolean vehicleSold;
    private double totalPrice;
    private double monthlyPayment;

    public Contract(String dateOfContract, String custumerName, String custumerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment) {
        this.dateOfContract = dateOfContract;
        this.custumerName = custumerName;
        this.custumerEmail = custumerEmail;
        this.vehicleSold = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
    }

    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustumerName() {
        return custumerName;
    }

    public void setCustumerName(String custumerName) {
        this.custumerName = custumerName;
    }

    public String getCustumerEmail() {
        return custumerEmail;
    }

    public void setCustumerEmail(String custumerEmail) {
        this.custumerEmail = custumerEmail;
    }

    public boolean isVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(boolean vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    public abstract void getTotalPrice();

    public abstract void getMonthlyPayment();
}
