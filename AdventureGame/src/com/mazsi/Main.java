package com.mazsi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, Location> location = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        location.put(0, new Location(0, "A gép előtt ülsz és épp Java-t tanulsz."));
        location.put(1, new Location(1, "Az út végén álsz egy öreg téglaépület előtt."));
        location.put(2, new Location(2, "A hegy tetején állsz."));
        location.put(3, new Location(3, "Egy épületben vagy, aminek az udvarán egy szökőkút áll."));
        location.put(4, new Location(4, "Egy nagy völgyben állsz."));
        location.put(5, new Location(5, "Egy erdőben vagy."));

        location.get(1).addExit("W",2);
        location.get(1).addExit("E",3);
        location.get(1).addExit("S",4);
        location.get(1).addExit("N",5);

        location.get(2).addExit("N",5);

        location.get(3).addExit("W",1);

        location.get(4).addExit("N",1);
        location.get(4).addExit("W",2);

        location.get(5).addExit("S",1);
        location.get(5).addExit("W",2);

        int loc = 1;
        while(true) {
            System.out.println(location.get(loc).getDescreption());
            if (loc == 0) {
                break;
            }

            Map<String, Integer> exits = location.get(loc).getExits();
            System.out.print("A következő írányokba mehetsz tovább: ");
            for(String exit : exits.keySet()) {
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();
            if (exits.containsKey(direction)) {
                loc = exits.get(direction);
            } else {
                System.out.println("Ebbe az irányba nem mehetsz!");
            }
        }

    }
}
