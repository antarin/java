package controller;

import classes.Todo;
import enums.Priority;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoController {

    public static final String DATEFORMAT = "yyyy.MM.dd";
    public static final int SORT_BY_NAME = 1;
    public static final int SORT_BY_DATE = 2;
    public static final int SORT_BY_URGENT = 3;
    private static Scanner scanner = new Scanner(System.in);
    public static final String MENU = "\t0  -  Kilépés\n" +
            "\t1  -  Új Tennivaló\n" +
            "\t2  -  Tennivalók listája\n" +
            "\t3  -  Tennivalók befejezése\n" +
            "\t4  -  Menü kiírása";

    private static List<Todo> todoList = new ArrayList<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);

    public static void main(String[] args) {
        System.out.println(MENU);
        boolean quit = false;
        while (!quit) {
            System.out.println("Válasz...");
            int action = getInt();
            switch (action) {
                case 0:
                    System.out.println("Visztlát!");
                    quit = true;
                    break;
                case 1:
                    getTodoItem();
                    break;
                case 2:
                    System.out.println("Hogy rendezem a teendőket? 1-Név szerint  2-Dátum szerint  3-Fontosság szerint");
                    listTodoItems(getInt());
                    break;
                case 3:
                    doneTodoItem();
                    break;
                case 4:
                    System.out.println(MENU);
                    break;

            }
        }

    }

    public static void getTodoItem() {
        todoList = FileController.loadFile();
        System.out.println("Add meg a tennivalót: ");
        String name = scanner.nextLine();
        System.out.println("Add meg a fontosságát(1-Nagyon fontos  2-Kevésbé fontos  3-Nem fontos ");
        Priority prior = null;
        do {
            int urgent = getInt();
            switch (urgent) {
                case 1:
                    prior = Priority.URGENT;
                    break;
                case 2:
                    prior = Priority.MEDIUM;
                    break;
                case 3:
                    prior = Priority.LOW;
                    break;
            }
        } while (prior == null);
        System.out.println("Add meg a dátumot a következő formátumban(" + DATEFORMAT + "): ");
        Date date = getDate();
        Todo item = new Todo(name, prior, date);
        todoList.add(item);
        FileController.saveFileTxt(todoList);
    }


    private static Date getDate() {
        Date value = null;

        do {
            try {
                value = sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } while (value == null);
        return value;
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

    private static void listTodoItems(int sort) {
        todoList = FileController.loadFile();
        todoList.sort(new Comparator<Todo>() {
            @Override
            public int compare(Todo o1, Todo o2) {
                switch (sort) {
                    case SORT_BY_URGENT:
                        return o1.getPriority().compareTo(o2.getPriority());
                    case SORT_BY_DATE:
                        return o2.getDate().compareTo(o1.getDate());
                    case SORT_BY_NAME:
                        return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }

                return 0;
            }
        });

        for (Todo item : todoList) {
            System.out.println(item.getName() + " | " + item.getPriority() + " | " + sdf.format(item.getDate()) + " | " + item.isDone());
        }
    }

    private static void doneTodoItem() {

        System.out.println("Melyik teendőt végezted el: ");
        String todoName = scanner.nextLine();
        for (Todo todo : todoList = FileController.loadFile()) {
            if (todo.getName().toLowerCase().equals(todoName.toLowerCase())) {
                todo.setDone(true);
            }

        }
        FileController.saveFileTxt(todoList);

    }
}