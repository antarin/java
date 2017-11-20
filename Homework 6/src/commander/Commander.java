package commander;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;


public class Commander {
    public static Map<Integer, File> fileSystem = new LinkedHashMap<>();

    public static String getProperties() {

        try (FileReader reader = new FileReader("config-file.properties")) {

            Properties properties = new Properties();
            properties.load(reader);
            reader.close();

            return properties.getProperty("root");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba");
            return null;
        }
    }


    public static void getFiles(File path, boolean show) {

        int i = 0;
        for (File file : path.listFiles()) {
            fileSystem.put(++i, file);
            if (show) {
                System.out.println(i + " " + file.getName());
            }
        }

    }

    public static void getFileTree(File[] path, boolean show, int deep, int index) {

        if (deep >= 0) {
            try {
                for (File file : path) {
                    index = index + 1;
                    fileSystem.put(index, file);
                    if (file.isDirectory()) {
                        getFileTree(file.listFiles(), show, (deep - 1), index);
                    }
                    System.out.println(index + " " + file.getAbsolutePath());
                }
            } catch (NullPointerException e) {
                System.out.println("Hibaaaaaa" + e.getMessage());
            }
        }
    }

    public static void copyFile(int origin, int dest) {
//        getFiles(MainController.currentPosition, false);
//        getFileTree(MainController.currentPosition.listFiles(), false, 15, 0);

        File file = fileSystem.get(origin);
        Path destt = Paths.get(fileSystem.get(dest).getAbsolutePath() + File.separator + file.getName());
        try {

            Files.copy(file.toPath(), destt);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba 2 " + e.getMessage());
        }
    }

    public static void moveFile(int origin, int dest) {
//        getFiles(MainController.currentPosition, false);
//        getFileTree(MainController.currentPosition.listFiles(), false, 15, 0);

        File file = fileSystem.get(origin);
        Path destt = Paths.get(fileSystem.get(dest).getAbsolutePath() + File.separator + file.getName());
        try {

            Files.move(file.toPath(), destt);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba 2 " + e.getMessage());
        }
    }
}
