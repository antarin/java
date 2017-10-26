package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;



public class Controller {

    @FXML private Canvas myCanvas;
    @FXML private TextField aSideTextField;
    @FXML private TextField bSideTextField;
    @FXML private TextField cSideTextField;
    @FXML private TextField angleTextField;
    @FXML private TextField arrayTextField;
    @FXML private Label angleLabel;
    @FXML private Label arrayLabel;
    @FXML private Button areaButton;
    @FXML private Button districtButton;
    @FXML private TableView<String> myTableView;

    public void initialize() {

        GraphicsContext gc = myCanvas.getGraphicsContext2D();

        areaButton.setDisable(true);
        districtButton.setDisable(true);

        gc.setFill(Color.GREEN);
        gc.setLineWidth(2);
        gc.strokeLine(0,50,25, 0);
        gc.strokeLine(25,0,100, 50);
        gc.setLineWidth(2);
        gc.strokeLine(100,50,0, 50);
        gc.setLineWidth(1);
        gc.strokeText("a", 1, 25);
        gc.strokeText("b", 70, 25);
        gc.strokeText("c", 40, 59);
        gc.strokeText("\u00B5", 25,40);
        gc.strokeArc(-13,30, 30, 40, 0, 65, ArcType.ROUND);

    }

    @FXML
    public void onButtonClick(ActionEvent e) {
        if (e.getSource().equals(districtButton)) {
            angleLabel.setText(String.valueOf(Double.parseDouble(aSideTextField.getText()) +
                    Double.parseDouble(bSideTextField.getText()) +
                    Double.parseDouble(cSideTextField.getText())));
        } else {
            angleLabel.setText(String.valueOf((Double.parseDouble(aSideTextField.getText()) *
                    Double.parseDouble(bSideTextField.getText()) *
                    Math.sin(Double.parseDouble(angleTextField.getText())))/2));
        }

    }

    @FXML
    public void handleOnKeyRelesed() {

        String aSide = aSideTextField.getText();
        String bSide = bSideTextField.getText();
        String cSide = cSideTextField.getText();
        String angle = angleTextField.getText();
        boolean disableDistrictButton = aSide.isEmpty() || aSide.trim().isEmpty() ||
                bSide.isEmpty() || bSide.trim().isEmpty() ||
                cSide.isEmpty() || cSide.trim().isEmpty();
        boolean disableAreaButton = aSide.isEmpty() || aSide.trim().isEmpty() ||
                bSide.isEmpty() || bSide.trim().isEmpty() || angle.isEmpty() || angle.trim().isEmpty();

        districtButton.setDisable(disableDistrictButton);
        areaButton.setDisable(disableAreaButton);
    }

    @FXML
    public void handleArray() {
        String arrayString = arrayTextField.getText();
        String myOrderedString = "";
        ObservableList<String> myList = FXCollections.observableArrayList();

        String[] itemPieces = arrayString.split(",");
        for(String myString : itemPieces) {
            myList.add(myString);
        }

        myTableView.setItems(myList.sorted());
        for(Object myString : myList.sorted()) {
            System.out.println(myString);
            myOrderedString += String.valueOf(myString) + ", " ;
        }

        arrayLabel.setText(myOrderedString);
    }
}
