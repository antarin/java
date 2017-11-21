package repeatersearch;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class RepeaterSearch {

    private static final String HTML_START = "<!DOCTYPE html>\n" +
            "<html lang='en'>\n" +
            "<head>\n" +
            "<title>HTML készításe java-ban</title>\n" +
            "<meta charset='utf-8'>\n" +
            "<meta name='viewport' content='width=device-width, initial-scale=1'>\n" +
            "<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css'>\n" +
            "<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>\n" +
            "<script src='https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js'></script>\n" +
            "<script src='https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js'></script>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div class='container'>\n" +
            "<h2>Szóösszetételes házi</h2>\n" +
            "<table class='table'>\n" +
            "<thead>\n" +
            "<tr>\n" +
            "<th>Szóösszetétel</th>\n" +
            "<th>Előfordulás</th>\n" +
            "</tr>\n" +
            "</thead>\n" +
            "<tbody>\n";


    private static final String HTML_END =
            "</tbody>\n" +
            "</table>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";





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
            while (ze != null) {
                File newFile = new File("D:" + File.separator + "Song" + File.separator + ze.getName());
                System.out.println("Unzipping to " + newFile.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }

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
        String allText = "";
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
        allText = allText.replaceAll("[-:<>.,\u0094\u0084=_\"']", "");
        System.out.println(allText);
        searchingRepeat(allText, 0);
    }

    private static void searchingRepeat(String text, int start) {

        int wordsCounter = Integer.parseInt(getProperties().get(1).toString());
        String[] parts = text.split(" ");
        String sb = "";
        Map<String, Integer> repeats = new LinkedHashMap<>();



        for (int i = 0; i < parts.length - wordsCounter; i++) {
            for (int j = 0; j < wordsCounter; j++) {
                sb = sb + parts[i + j] + " ";
            }

            if (repeats.containsKey(sb)) {
                repeats.replace(sb, repeats.get(sb) + 1);
            } else {
                repeats.put(sb, 1);
            }
            sb = "";
        }
        for (String repeat : repeats.keySet()) {
            System.out.println(repeat + repeats.get(repeat));

        }

        writePairs(repeats);
    }

    private static void writePairs(Map<String, Integer> repeats) {

        repeats = sortByValue(repeats);

        try (FileWriter fileWriter = new FileWriter("D:" + File.separator + "Song" + File.separator + "song.html")) {
            fileWriter.write(HTML_START);
            for(String repeat  : repeats.keySet()) {
                fileWriter.write("<tr>\n<td>" + repeat + "</td>\n<td>" + repeats.get(repeat) + "</td>\n</tr>");
            }
            fileWriter.write(HTML_END);
        } catch (IOException e) {
            System.out.println("Hiba " + e.getMessage());
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
