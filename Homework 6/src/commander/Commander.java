package commander;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


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
            for (File file : path) {
                try {
                    if (fileSystem.containsKey(index)) {
                        index = fileSystem.size();
                    }
                    index++;
                    fileSystem.put(index, file);
                    if (file.isDirectory()) {
                        getFileTree(file.listFiles(), show, (deep - 1), index);
                    }
                    System.out.println(index + " " + file.getAbsolutePath());
                } catch (NullPointerException e) {

                }
            }
        }
    }

    public static void copyFile(int origin, int dest) {

        File file = fileSystem.get(origin);
        Path destination = Paths.get(fileSystem.get(dest).getAbsolutePath() + File.separator + file.getName());
        try {

            Files.copy(file.toPath(), destination);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba 2 " + e.getMessage());
        }
    }

    public static void moveFile(int origin, int dest) {

        File file = fileSystem.get(origin);
        Path destination = Paths.get(fileSystem.get(dest).getAbsolutePath() + File.separator + file.getName());
        try {

            Files.move(file.toPath(), destination);
            file.delete();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba 2 " + e.getMessage());
        }
    }

    public static void zipFiles(int[] index) {

        try {
            byte[] b = new byte[1024];
            int count;
            File input = null;
            FileInputStream fileInput = null;
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(getProperties() + File.separator + "tmp.zip"));
            for (int i : index) {
                input = fileSystem.get(i);
                System.out.println(fileSystem.get(i).getName());
                fileInput = new FileInputStream(input.getAbsoluteFile());
                out.putNextEntry(new ZipEntry(input.getName()));
                while ((count = fileInput.read(b)) > 0) {
                    out.write(b, 0, count);
                }
            }

            fileInput.close();
            out.close();

        } catch (IOException e) {
            System.out.println("Hiba a zip-ben.");
            e.printStackTrace();
        }
    }
}
