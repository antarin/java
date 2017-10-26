package com.mazsi;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int x = getInt();
        System.out.println("Az x = " + x);
    }

    private static int divideLBYL(int x, int y) {
        if (y != 0) {
            return x / y;
        }

        return 0;
    }

    private static int divideEAFP(int x, int y) {
        try {
            return x / y;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    private static int getInt() {
        return scanner.nextInt();
    }

    private static int getIntLBYL() {
        boolean isValed = true;
        System.out.println("Kérlek adj meg egy számot");
        String input = scanner.next();
        for(int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) {
                isValed = false;
                break;
            }
        }
        if (isValed) {
            return Integer.parseInt(input);

        }

        return 0;
    }

    private static int getIntEAFP() {
        System.out.println("Kérlek adj meg egy számot");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            return 0;
        }
    }
}
