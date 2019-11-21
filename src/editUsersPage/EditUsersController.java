package editUsersPage;

import adminPage.AdminView;
import application.FermiConnector;
import application.FermiEntry;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class EditUsersController {

    private FermiConnector db = new FermiConnector();

    private FermiEntry user;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab addTab, editTab, removeTab;

    @FXML
    private Button addButton, homeButton, removeTabSearchButton, editTabSearchButton, deleteButton;

    @FXML
    private TextField firstNameField, lastNameField, phoneField, seniorityField, hoursOfferedField,
            removeTabSearchField, editTabSearchField, editFirstNameField, editLastNameField,
            editPhoneField, editSeniorityField, editHoursField;

    @FXML
    private CheckBox bisonProgramCheckBox, adminCheckBox;

    @FXML
    private Label result, firstNameText, lastNameText, phoneText, seniorityText, hoursText, removeResult;

    public EditUsersController() {
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void addUser() {
        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String phone = phoneField.getText();
        Double hours = Double.parseDouble(hoursOfferedField.getText());
        Integer seniority = Integer.parseInt(seniorityField.getText());
        Boolean bison = bisonProgramCheckBox.isSelected();

        FermiEntry entry = new FermiEntry(fName, lName, phone, hours, seniority, bison);

        if (db.add(entry)) {
            result.setText("Employee Added");
        } else {
            result.setText("Action failed");
        }

    }

    @FXML
    private void showHome() throws Exception {
        Stage stage = (Stage) homeButton.getScene().getWindow();

        AdminView view = new AdminView();
        view.showView(stage);
    }

    @FXML
    private void displayUserFromSearch() {
        if (tabPane.getSelectionModel().getSelectedItem().getId().equals("editTab")) {
            String entry = editTabSearchField.getText();
            user = searchUsers(entry);
            setTextFields();
        } else if (tabPane.getSelectionModel().getSelectedItem().getId().equals("removeTab")) {
            String entry = removeTabSearchField.getText();
            user = searchUsers(entry);
            setTextLabels();
        }
        // TODO: check if user is null and display that user was not found
    }

    private void setTextFields() {
        editFirstNameField.setText(user.getFirstName());
        editLastNameField.setText(user.getLastName());
        editPhoneField.setText(user.getPhone());
        editSeniorityField.setText(user.getSeniority().toString());
        editHoursField.setText(user.getOvertime().toString());
    }

    private void setTextLabels() {
        firstNameText.setText(user.getFirstName());
        lastNameText.setText(user.getLastName());
        phoneText.setText(user.getPhone());
        seniorityText.setText(user.getSeniority().toString());
        hoursText.setText(user.getOvertime().toString());
    }

    private FermiEntry searchUsers(String entry) {
        ArrayList<FermiEntry> data = FermiEntry.getEmployees(db);
        for (FermiEntry employee : data) {
            if (employee.getFirstName().equalsIgnoreCase(entry) || employee.getLastName().equalsIgnoreCase(entry)) {
                user = employee;
            }
        }
        return user;
    }

    @FXML
    private void deleteUser() {
        if (db.remove(user)) {
            String str = String.format("User: %s %s was deleted.", user.getFirstName(), user.getLastName());
            removeResult.setText(str);
        } else {
            String str = String.format("Error deleting user: %s %s.", user.getFirstName(), user.getLastName());
            removeResult.setText(str);
        }
    }
}
