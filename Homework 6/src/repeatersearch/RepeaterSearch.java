package repeatersearch;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RepeaterSearch {

    private static List getProperties() {

        try (FileReader reader = new FileReader("config-file.properties")) {

            List prop = new ArrayList<>();
            Properties properties = new Properties();
            properties.load(reader);
            reader.close();
            prop.add(properties.getProperty("path"));
            prop.add(properties.getProperty("number"));

            return prop;

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Hiba");
            return null;
        }
    }

    public static void unzip() {

        File dir = new File("D:" + File.separator + "Song" + File.separator);
        FileInputStream fis;
        byte[] buffer = new byte[1024];

        if (!dir.exists()) {
            dir.mkdirs();
        }


        try {
            fis = new FileInputStream(getProperties().get(0).toString());
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
//            while (ze != null) {
//                File newFile = new File("D:" + File.separator + "Song" + File.separator + ze.getName());
//                System.out.println("Unzipping to " + newFile.getAbsolutePath());
//                FileOutputStream fos = new FileOutputStream(newFile);
//                int len;
//                while ((len = zis.read(buffer)) > 0) {
//                    fos.write(buffer, 0, len);
//                }
//                fos.close();
//                //close this ZipEntry
//                zis.closeEntry();
//                ze = zis.getNextEntry();
//            }

            zis.closeEntry();
            zis.close();
            fis.close();
            readSongs(dir.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void readSongs(String file) {


        File songs = new File(file);
        String allText = null;
        try {

            for (File fileName : songs.listFiles()) {
                String line;
                RandomAccessFile input = new RandomAccessFile(fileName.getAbsoluteFile(), "r");
                while ((line = input.readLine()) != null) {
                    allText = allText + line;
                }
            }

        } catch (IOException e) {
            System.out.println("Hiba!!!" + e.getMessage());
        }

        allText = allText.replaceAll(":*?<>.,=(\")']", "");
        searchingRepeat(allText,0);
    }

    private static Map searchingRepeat(String text, int start) {

        int wordsCounter = Integer.parseInt(getProperties().get(1).toString());
        int counter = 0;
        String[] parts = text.split(" ");
        String sb = "";
        String sb2 = "";
        Map<String, Integer> pairs = new HashMap<>();

        for (int i = 0; i < wordsCounter; i++) {
            if (i <= parts.length - 1) {
                sb = sb + parts[start+i];
            }
        }
        System.out.println(sb);

        try {

            for (int i = 0; i < parts.length; i++) {
                sb2 = "";
                    for (int j = 0; j < wordsCounter; j++) {
                        if ((j+i) <= parts.length - j) {
                            sb2 = sb2 + parts[j+i];
                        }
                    }
                System.out.println(sb2);
                    if (sb == sb2) {
                        counter++;
                    }
            }

        } catch (NullPointerException e) {
            System.out.println("Hiba");
        }
        System.out.println(counter);
        pairs.put(sb, counter);
        start++;
        if (start <= text.length()) {
            searchingRepeat(text, start);
        }
        return pairs;
    }

    private static void writePairs(Map pairs) {
        System.out.println("1");
    }
}
