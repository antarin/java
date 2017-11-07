package com.mazsi;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IOFiles {

    public static void saveData() {

        //Tudom ez nem a legszebb megoldás a Map indexelésére és nem is igazán hatékony mert
        //Pl. törlésnél már az utolsót írja felül, de hirtelen nem jutot más eszembe.
        //Mellesleg számlát törölni nem is törvényes csak stórnozni :)
        Map<Integer, Invoice> invoiceData = loadInvoiceFile();

        try (DataOutputStream dataFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")))) {
            dataFile.writeInt(invoiceData.size());
        } catch (IOException e) {
            System.out.println("IO Exception: 0 " + e.getMessage());
        }
    }

    public static int loadData() {
        int index = 0;
        try (DataInputStream dataFile = new DataInputStream(new BufferedInputStream(new FileInputStream("data.dat")))) {
             index = dataFile.readInt();
        } catch (IOException e) {
            System.out.println("IO Exception: 1 " + e.getMessage());
        }
        return index;
    }

    public static void saveFile(String fileName, Map saveMap) {

        try (ObjectOutputStream payFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))){

            for (Object item : saveMap.values()) {
                payFile.writeObject(item);
            }
        } catch (IOException e) {
            System.out.println("IO Exception: 2 " + e.getMessage());
        }
    }

    public static Map<Integer, Invoice> loadInvoiceFile() {

        Map<Integer, Invoice> loadMaps = new HashMap<>();

        try (ObjectInputStream payFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("invoice.dat")))){
            boolean eof = false;
            while (!eof) {
                try {

                    Invoice loadMap = (Invoice) payFile.readObject();
                    loadMaps.put(loadMap.getInvoiceNum(), loadMap);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
                System.out.println("IO Exception: 3 " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found Exception:" + e.getMessage());
        }
        return loadMaps;
    }

    public static Map<String, Employee> loadEmployeeeFile() {

        Map<String, Employee> loadMaps = new HashMap<>();

        try (ObjectInputStream payFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("employee.dat")))){
            boolean eof = false;
            while (!eof) {
                try {

                    Employee loadMap = (Employee) payFile.readObject();
                    loadMaps.put(loadMap.getName(), loadMap);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception: 3 " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found Exception:" + e.getMessage());
        }
        return loadMaps;
    }
}
