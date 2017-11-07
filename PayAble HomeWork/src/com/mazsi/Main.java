package com.mazsi;




import Classes.Invoice;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Main {

    private static Map<Integer, Invoice> invoiceMap =  new HashMap<>();

    private static Scanner scanner = new Scanner(System.in);
    private static int data = 0;

    public static void main(String[] args) {

        printMenu();
        boolean quit = false;
        while (!quit) {
            System.out.println("Válasz...");
            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 0:
                    quit = true;
                    System.out.println("Visztlát!");
                    break;
                case 1:
                    com.mazsi.Main.newInvoice();
                    break;
                case 2:
                    printInvoice();
                    break;
                case 3:
                    remoceInvoice();
                    break;
                case 4:
                    EmployeeMenu.employeeMenu();
                    break;
                case 5:
                    printMenu();
                    break;
            }
        }
    }


    private static void printMenu() {
        System.out.println("\t0  -  Kilépés\n" +
                "\t1  -  Új számla\n" +
                "\t2  -  Számlák listája\n" +
                "\t3  -  Számla törlése\n" +
                "\t4  -  Dolgozók\n" +
                "\t5  -  Menü kiírása\n");
    }


    private static void newInvoice() {
        System.out.println("Adja meg az adatokat a következő formában: Cikkszám,Cikkmegnevezés,Darabszám,Ár");
        String getInvoiceData = scanner.nextLine();

        //Direkt nem egyesével kértem be az adatokat.
        String[] invoicePiece = getInvoiceData.split(",");

        Invoice invoice = new Invoice(data++ ,
                invoicePiece[0],
                invoicePiece[1],
                Integer.parseInt(invoicePiece[2]),
                Integer.parseInt(invoicePiece[3]));

        invoiceMap.put(invoice.getInvoiceNum(), invoice);
    }


    private static void printInvoice() {
        for (Invoice item : invoiceMap.values()) {
            System.out.println("Cikkszám: " + item.getPartNumber() +
                    ", cikk: " + item.getPartDescreption() + ", a számla végösszege: " +
                    item.getPaymentAmount() + ", ID: " + String.format("%04d", item.getInvoiceNum()));
        }
    }

    private static void remoceInvoice() {
        System.out.println("Kérem adja meg a számla számát a 0-k nélkül: ");
        int name = scanner.nextInt();
        invoiceMap.remove(name);
    }
}



