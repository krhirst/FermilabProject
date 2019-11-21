package editUsersPage;

import adminPage.AdminView;
import application.FermiConnector;
import application.FermiEntry;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class EditUsersController {

    private FermiConnector db = new FermiConnector();

    private FermiEntry user;

    @FXML
    private Button addButton, homeButton, searchButton, deleteButton;

    @FXML
    private TextField firstNameField, lastNameField, phoneField, seniorityField, hoursOfferedField,
                        searchField;

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
    private void startSearch() {
        String entry = searchField.getText();
        user = searchUsers(entry);
        if (user == null) {
            firstNameText.setText("null");
            lastNameText.setText("null");
            phoneText.setText("null");
            seniorityText.setText("null");
            hoursText.setText("null");
        } else {
            firstNameText.setText(user.getFirstName());
            lastNameText.setText(user.getLastName());
            phoneText.setText(user.getPhone());
            seniorityText.setText(user.getSeniority().toString());
            hoursText.setText(user.getOvertime().toString());
        }

    }

    private FermiEntry searchUsers(String entry) {
        ArrayList<FermiEntry> data = FermiEntry.getEmployees(db);
        for (FermiEntry employee : data) {
            if (employee.getLastName().equalsIgnoreCase(entry)) {
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
