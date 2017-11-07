package com.mazsi;

public class BasePlusCommissionEmployee extends CommissionEmployee {

    private double baseSalay;

    public BasePlusCommissionEmployee(String firstName,
                                      String lastName, String socialSecurityNumber,
                                      double grossSales, double commisionRate, double baseSalay) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commisionRate);
        this.baseSalay = baseSalay;
    }

    @Override
    public double getPaymentAmount() {
        return super.getPaymentAmount() + this.baseSalay;
    }
}
