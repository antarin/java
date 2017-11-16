package classes;

import controller.TodoController;
import enums.Priority;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Todo {
    private SimpleDateFormat sdf = new SimpleDateFormat(TodoController.DATEFORMAT);

    private String name;
    private Priority priority;
    private Date date;
    private boolean done;

    public Todo(String name, Priority priority, Date date) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.done = false;
    }

    public Todo(String name, Priority priority, Date date, boolean done) {
        this.name = name;
        this.priority = priority;
        this.date = date;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

//    public int compareTo(Todo item) {
//        return this.name.compareToIgnoreCase(item.getName());
//    }

    @Override
    public String toString() {
        return this.name + ";" + this.priority + ";" + sdf.format(this.date) + ";" + this.done + ";";
    }


}
