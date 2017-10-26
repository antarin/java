package sample;

import javafx.beans.property.SimpleStringProperty;

public class Contacts {

    private String firstName;
    private String lastName;
    private String phone;
    private String notes;

    public Contacts(String firstName, String lastName, String phone, String notes) {
        this.firstName= firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.notes = notes;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return  firstName;
    }
}
