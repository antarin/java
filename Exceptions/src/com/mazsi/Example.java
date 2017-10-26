package com.mazsi;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Example {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int result = devide();
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println(e.toString());
        }
    }

    private static int devide() {

        int x,y;

        try {
            x = getInt();
            y = getInt();
            System.out.println("Az x " + x + ", az y " + y);
            return x / y;
        } catch (NoSuchElementException e) {
            throw new ArithmeticException("nem megfelelő bevitel");
        } catch (ArithmeticException e) {
            throw new ArithmeticException("nullával probált meg osztani");
        }
    }

    private static int getInt() {
        System.out.println("Kérlek adj meg egy számot: ");
        while(true) {

            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Kérem számot adjon meg!");
            }
        }
    }


}
