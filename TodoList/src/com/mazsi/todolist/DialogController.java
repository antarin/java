package com.mazsi.todolist;

import com.mazsi.todolist.datamodel.TodoData;
import com.mazsi.todolist.datamodel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class DialogController {

    @FXML
    private TextField shortDescreptionField;

    @FXML
    private TextArea detailsArea;

    @FXML
    private DatePicker deadlinePicker;

    public TodoItem proccesResult() {
        String shortDescreption = shortDescreptionField.getText().trim();
        String details = detailsArea.getText().trim();
        LocalDate deadline = deadlinePicker.getValue();

        TodoItem newItem = new TodoItem(shortDescreption, details, deadline);
        TodoData.getInstance().addListItem(newItem);
        return newItem;
    }
}
