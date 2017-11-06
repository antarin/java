package com.mazsi;

import Interface.Payable;

import java.io.Serializable;

public class Invoice implements Payable, Serializable {

    private static final long serialVersionUID = 1L;

    private int invoiceNum = 0;
    private String partNumber;
    private  String partDescreption;
    private int quantity;
    private int pricePerItem;

    public Invoice(int invoiceNum, String partNumber, String partDescreption, int quantity, int pricePerItem) {
        this.invoiceNum = invoiceNum;
        this.partNumber = partNumber;
        this.partDescreption = partDescreption;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescreption() {
        return partDescreption;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPricePerItem() {
        return pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return 0;
    }
}
