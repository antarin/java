package com.mazsi;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class IOFiles {

    public static void saveFile(String fileName, Map saveMap) {

        try (ObjectOutputStream payFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))){

            for (Object item : saveMap.values()) {
                payFile.writeObject(item);
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

    public static Map<Integer, Invoice> loadInvoiceFile() {

        Map<Integer, Invoice> loadMaps = new HashMap<>();

        try (ObjectInputStream payFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("invoice.dat")))){
            boolean eof = false;
            while (!eof) {
                try {

                    Invoice loadMap = (Invoice) payFile.readObject();
                    System.out.println("Cikkszám: " + loadMap.getPartNumber() +
                            ", cikk: " + loadMap.getPartDescreption() + ", ID: " + String.format("%04d", loadMap.getInvoiceNum()));
                    loadMaps.put(loadMap.getInvoiceNum(), loadMap);
                } catch (EOFException e) {
                    eof = true;
                }
            }
        } catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found Exception:" + e.getMessage());
        }
        return loadMaps;
    }
}
