package com.mazsi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Theatre myTheatre = new Theatre("Madács színház", 10, 12);

        if (myTheatre.reserveSeat("D12")) {
            System.out.println("Kérem a jegy árát.");
        } else {
            System.out.println("A hely már foglalt!");
        }
        System.out.println();
        if (myTheatre.reserveSeat("B13")) {
            System.out.println("Kérem a jegy árát.");
        } else {
            System.out.println("A hely már foglalt!");
        }

        List<Theatre.Seat> reverseList = new ArrayList<>(myTheatre.getSeats());
        Collections.reverse(reverseList);
        printList(reverseList);

        List<Theatre.Seat> priceList = new ArrayList<>(myTheatre.getSeats());
        priceList.add(myTheatre.new Seat("B00", 13));
        priceList.add(myTheatre.new Seat("A00", 13));
        Collections.sort(priceList, Theatre.PRICE_ORDER);
        printList(priceList);

    }

    public static void printList(List<Theatre.Seat> list) {
        int counter = 0;
        for(Theatre.Seat seat : list) {
            counter++;
            System.out.print(" " + seat.getSeatNumber() + " " + seat.getPrice());
            if (counter == 12) {
                System.out.println();
                counter = 0;
            }
        }
        System.out.println();
        System.out.println("=================================================");
    }


}
