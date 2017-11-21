//  konzolos filekezelő
//  ki tud íratni a mappában lévő file-okat és rekurzívan is. (Ugy is, hogy számmot addunk meg és addig a mélységig.)
//  minden file és mappa előtt legyen egy szám és ahozz egy olyan opció, hogy mit szeretnénk csinálni vele.
//  másolás, mozgatás,
//  Kérjen be számokat és azokat csomagolja össze egy zip-be.
//  lehessen mozogni a mappák között (root-tól kezdve) a rootot property file-ból kapja meg.
//
//
//
//  Szorg: Property file-ból jön egy path és egy szám. Megkeresi a path-t ott lesz egy zip (létezik-e??) és ha igen
//  kicsomagolja éslesz benne pl 5 txt. Ezekben dalszövegek. Beolvassa az összes dalt és vizsgálja a szó ismétléseket.
//  A szám azt adja meg, hogy hány szó ismétlését vizsgéljuk (Pl.: ha 2 akkor "kék az" ...  "az ég"..
//  Vesszőket, gondolat jeleket szedje ki.
//  Ha ez meg van akkor ki kell íratni csökkenő sorendeben a szóismétléseket és az hányszor fordult elő
//  egy .html file-ba

package main;

import commander.Commander;
import repeatersearch.RepeaterSearch;

import java.io.File;
import java.util.Scanner;

public class MainController {

    public static File currentPosition = new File(Commander.getProperties() + File.separator);

    private static Scanner scanner = new Scanner(System.in);
    public static final String MENU = "\t0  -  Kilépés\n" +
            "\t1  -  Új Tennivaló\n" +
            "\t2  -  Tennivalók listája\n" +
            "\t3  -  Tennivalók befejezése\n" +
            "\t4  -  Menü kiírása";


    public static void main(String[] args) {


//        System.out.println(MENU);
        boolean quit = false;
        while (!quit) {
            System.out.print(currentPosition.getPath() + " ");
            String action = scanner.nextLine();
            String[] order = action.split(" ");

            switch (order[0].toLowerCase()) {
                case "quit":
                    System.out.println("Visztlát!");
                    quit = true;
                    break;
                case "dir":
                    Commander.getFiles(currentPosition, true);
                    break;
                case "dirall":
                    Commander.getFileTree(currentPosition.listFiles(), true, Integer.parseInt(order[1]), 0);
                    break;
                case "copy":
                    Commander.copyFile(Integer.parseInt(order[1]), Integer.parseInt(order[2]));
                    break;
                case "move":
                    Commander.moveFile(Integer.parseInt(order[1]), Integer.parseInt(order[2]));
                    break;
                case "zip":
                    Commander.zipFiles(getNumbers(order));
                    break;
                case "cd":
                    if (order[1].equals("..")) {
                        order[1] = currentPosition.getAbsolutePath().substring(currentPosition.getAbsolutePath().lastIndexOf(File.separator) + 1);
                        order[1] = currentPosition.getAbsolutePath().replace(order[1], "");
                        System.out.println(order[1]);
                        currentPosition = new File(order[1]);

                    } else {
//                        if (!Commander.fileSystem.get(order[1]).i()) {
//                            System.out.println("Nincs ilyen mappa");
//                            break;
//                        }
                        currentPosition = new File(currentPosition.getAbsolutePath() + File.separator + order[1]);
                    }
                    break;
                case "repeater":
                    RepeaterSearch.unzip();
                    break;
            }
        }

    }

    private static int getInt() {
        int value = -1;
        do {
            try {
                value = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Hibás adatbevitel!");
            }
        } while (value == -1);
        return value;
    }

    private static int[] getNumbers(String[] numbers) {

        int[] fileIndex = new int[numbers.length-1];
        try {
            for (int index = 0; index < numbers.length; index ++) {
                if (index != 0) {
                    fileIndex[index-1] = Integer.parseInt(numbers[index]);
                }
            }

            return fileIndex;

        } catch (NumberFormatException e) {
            System.out.println("Hibás adat!");
            return  null;
        }

    }
}
