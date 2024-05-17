package org.example;

public class SalesConstract extends Contract{
    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;

    private boolean finance;
    private double financeMonthlyPayment;

    public SalesConstract(String dateOfContract, String custumerName, String custumerEmail, boolean vehicleSold, double totalPrice, double monthlyPayment, double salesTaxAmount, double recordingFee, double processingFee, boolean finance, double financeMonthlyPayment) {
        super(dateOfContract, custumerName, custumerEmail, vehicleSold, totalPrice, monthlyPayment);
        this.salesTaxAmount = salesTaxAmount;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.finance = finance;
        this.financeMonthlyPayment = financeMonthlyPayment;
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

    public double getFinanceMonthlyPayment() {
        return financeMonthlyPayment;
    }

    public void setFinanceMonthlyPayment(double financeMonthlyPayment) {
        this.financeMonthlyPayment = financeMonthlyPayment;
    }


    @Override
    public void getTotalPrice() {

    }

    @Override
    public void getMonthlyPayment() {

    }
}
