package controller;

import classes.Todo;
import enums.Priority;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileController {

    private static final String FILENAMETXT = "todo.txt";

    public static void saveFileTxt(List<Todo> listItems) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAMETXT, "rw")) {
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
            String line;

            while ((line = raf.readLine()) != null) {
                    String[] lineElements = line.split(";");
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
            }

            return todoList;

        } catch (IOException e) {
            System.out.println("Hiba a file megnyitásakor: " + e.getMessage());
            return null;
        }
    }

    public static void saveTodoItem(List<Todo> listItems, Todo item) {
        try (RandomAccessFile raf = new RandomAccessFile(FILENAMETXT, "rw")) {

            String line;
            while ((line = raf.readLine()) != null) {
                if (line.contains(item.getName())) {
                    raf.seek(raf.getFilePointer() - (line.length() + System.lineSeparator().length()));
                    raf.writeBytes(item.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Hiba!" + e.getMessage());
        }
    }
}
