package com.mazsi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private static Map<Integer, Invoice> invoiceMap = IOFiles.loadInvoiceFile();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean quit = false;
//
//        for (Invoice i :  invoiceMap.values()) {
//            System.out.println(i.getPricePerItem() + ", " + i.getQuantity());
//        }

        System.out.println(invoiceMap.size());
        String getInvoiceData = scanner.nextLine();
        String[] invoicePiece = getInvoiceData.split(",");


        Invoice invoice = new Invoice(invoiceMap.size()+1,
                invoicePiece[0],
                invoicePiece[1],
                Integer.parseInt(invoicePiece[2]),
                Integer.parseInt(invoicePiece[3]));

        invoiceMap.put(invoice.getInvoiceNum(), invoice);

        System.out.println(invoiceMap.size());


//        IOFiles.saveFile("invoice.dat", invoiceMap);
        Map<Integer, Invoice> newMap = IOFiles.loadInvoiceFile();
    }

}
