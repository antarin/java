package com.mazsi;

public class CommissionEmployee extends Employee {

    private double grossSales;
    private double commisionRate;

    public CommissionEmployee(String firstName, String lastName,
                              String socialSecurityNumber, double grossSales, double commisionRate) {
        super(firstName, lastName, socialSecurityNumber);
        this.grossSales = grossSales;
        this.commisionRate = commisionRate;
    }

    @Override
    public double getPaymentAmount() {
        return this.grossSales * this.commisionRate;
    }
}
