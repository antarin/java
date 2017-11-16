package controller;

import classes.Todo;
import com.sun.xml.internal.ws.wsdl.parser.ParserUtil;
import enums.Priority;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class FileController {

    private static final String FILENAMETXT = "todo.txt";

    public static void saveFileTxt(List<Todo> listItems) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAMETXT, "rw")) {
//            if (listItems.size() > 1) {
//                raf.seek(raf.length());
//            }
            for (Todo item : listItems) {
                raf.writeBytes(item.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Hiba!" + e.getMessage());
        }
    }

    public static List<Todo> loadFile() {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAMETXT, "rw")) {

            List<Todo> todoList = new ArrayList<>();
            String line = null;
            boolean eof = false;

            while (!eof) {
                try {
                    line = raf.readLine();
                    String[] lineElements = line.split(";");
                    System.out.println(lineElements.length);
                    String name = lineElements[0];
                    Priority prior = Priority.valueOf(lineElements[1]);

                    Date value = null;
                    SimpleDateFormat sdf  = new SimpleDateFormat(TodoController.DATEFORMAT);
                    try {
                        value = sdf.parse(lineElements[2]);
                    } catch (ParseException e) {
                        System.out.println("Hiba");
                    }

                    Boolean done = Boolean.parseBoolean(lineElements[3]);
                    if (!done) {
                        todoList.add(new Todo(name, prior, value, done));
                    }
                } catch (Exception e) {
                    eof = true;
                }

            }

            return todoList;

        } catch (IOException e) {
            System.out.println("Hiba a file megnyitásakor: " + e.getMessage());
            return null;
        }
    }
    public static void saveFileTxt2(List<Todo> listItems) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAMETXT, "rw")) {
//            if (listItems.size() > 1) {
//                raf.seek(raf.length());
//            }
            for (Todo item : listItems) {
                raf.writeBytes(item.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Hiba!" + e.getMessage());
        }
    }
}
