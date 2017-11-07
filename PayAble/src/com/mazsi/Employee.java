package com.mazsi;

import Interface.Payable;

import java.io.Serializable;

public class Employee implements Payable, Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String socialSecurityNumber;

    public Employee(String firstName, String lastName, String socialSecurityNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
    }

    @Override
    public double getPaymentAmount() {
        return 0;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }
}
