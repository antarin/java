package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;


public class Controller {

    private ObservableList<Contacts> listContacts = FXCollections.observableArrayList();

//    @FXML private TableView<Contacts> contactsTableView = new TableView<>();
    @FXML private TableView contactsTableView;

    public void initialize() {

        Contacts item1 = new Contacts("Miklós", "Róna", "+36303831183", null);
        Contacts item2 = new Contacts("István", "Nagy", "+36304445556", null);
        Contacts item3 = new Contacts("Katalin", "Orosz", "+36708651587", null);
        Contacts item4 = new Contacts("Márta", "Zugló", "+36204315795", "Kispista");

        listContacts.add(item1);
        listContacts.add(item2);
        listContacts.add(item3);
        listContacts.add(item4);

        contactsTableView.setItems(listContacts.sorted());




    }

    public void closeClick() {
        Platform.exit();
    }
}
