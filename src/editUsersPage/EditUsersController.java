package editUsersPage;

import adminPage.AdminView;
import application.FermiConnector;
import application.FermiEntry;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.regex.Pattern;


public class EditUsersController {

    private FermiConnector db = new FermiConnector();

    private FermiEntry user;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab addTab, editTab, removeTab;

    @FXML
    private Button addButton, homeButton, removeTabSearchButton, editTabSearchButton, deleteButton, editButton;

    @FXML
    private TextField firstNameField, lastNameField, phoneField, seniorityField, hoursOfferedField,
            removeTabSearchField, editTabSearchField, editFirstNameField, editLastNameField,
            editPhoneField, editSeniorityField, editHoursField;

    @FXML
    private CheckBox bisonProgramCheckBox, adminCheckBox;

    @FXML
    private Label result, firstNameText, lastNameText, phoneText, seniorityText, hoursText, removeResult, editResult;

    public EditUsersController() {
    }

    @FXML
    public void initialize() {

    }

    @FXML
    private void showHome() throws Exception {
        Stage stage = (Stage) homeButton.getScene().getWindow();

        AdminView view = new AdminView();
        view.showView(stage);
    }

    @FXML
    private void addUser() {
        if (validateEntries()) {
            String fName = firstNameField.getText();
            String lName = lastNameField.getText();
            String phone = parsePhoneNumber(phoneField.getText());
            Double hours = Double.parseDouble(hoursOfferedField.getText());
            Integer seniority = Integer.parseInt(seniorityField.getText());
            Boolean bison = bisonProgramCheckBox.isSelected();

            FermiEntry entry = new FermiEntry(fName, lName, phone, hours, seniority, bison);

            if (db.add(entry)) {
                result.setText("Employee Added");
            } else {
                result.setText("Action failed");
            }
        } else {
            // TODO: Identify invalid fields
        }
    }

    @FXML
    private void displayUserFromSearch() {
        if (tabPane.getSelectionModel().getSelectedItem().getId().equals("editTab")) {
            if (validateString(editTabSearchField)) {
                String entry = editTabSearchField.getText();
                user = searchUsers(entry);
                setTextFields();
            }

        } else if (tabPane.getSelectionModel().getSelectedItem().getId().equals("removeTab")) {
            if (validateString(removeTabSearchField)) {
                String entry = removeTabSearchField.getText();
                user = searchUsers(entry);
                setTextLabels();
            }
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
            if (employee.getLastName().equalsIgnoreCase(entry)) {
                user = employee;
            }
        }
        return user;
    }

    @FXML
    private void editUser() {
        if (validateEntries()) {
            int originalSeniority = user.getSeniority();

            String fName = editFirstNameField.getText();
            String lName = editLastNameField.getText();
            String phone = editPhoneField.getText();
            Double hours = Double.parseDouble(editHoursField.getText());
            Integer seniority = Integer.parseInt(editSeniorityField.getText());

            user.setFirstName(fName);
            user.setLastName(lName);
            user.setPhone(phone);
            user.setOvertime(hours);
            user.setSeniority(seniority);

            if (db.edit(user, originalSeniority)) {
                String str = String.format("User: %s %s was updated.", user.getFirstName(), user.getLastName());
                editResult.setText(str);
            } else {
                String str = String.format("Error updating user: %s %s.", user.getFirstName(), user.getLastName());
                editResult.setText(str);
            }
        }

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

    private String parsePhoneNumber(String text) {
        StringBuilder phoneNumber = new StringBuilder();

        String areaCode = text.substring(0, 2);
        String first3 = text.substring(3, 6);
        String last4 = text.substring(6);

        phoneNumber.append("(" + areaCode + ")");
        phoneNumber.append(first3 + "-" + last4);

        return phoneNumber.toString();
    }

    private boolean validateEntries() {
        ArrayList<TextField> invalidEntries = new ArrayList<>();
        if (tabPane.getSelectionModel().getSelectedItem().getId().equals("addTab")) {
            if (!validateString(firstNameField))
                invalidEntries.add(firstNameField);
            if (!validateString(lastNameField))
                invalidEntries.add(lastNameField);
            if (!validatePhone(phoneField))
                invalidEntries.add(phoneField);
            if (!validateSeniority(seniorityField))
                invalidEntries.add(seniorityField);
            if (!validateHours(hoursOfferedField))
                invalidEntries.add(hoursOfferedField);
        } else if (tabPane.getSelectionModel().getSelectedItem().getId().equals("editTab")) {
            if (!validateString(editFirstNameField))
                invalidEntries.add(editFirstNameField);
            if (!validateString(editLastNameField))
                invalidEntries.add(editLastNameField);
            if (!validatePhone(editPhoneField))
                invalidEntries.add(editPhoneField);
            if (!validateSeniority(editSeniorityField))
                invalidEntries.add(editSeniorityField);
            if (!validateHours(editHoursField))
                invalidEntries.add(editHoursField);
        }

        if (invalidEntries.isEmpty()) {
            return true;
        } else {
            for (TextField field: invalidEntries) {
                field.getStyleClass().add("error");
            }
            return false;
        }
    }

    private boolean validateString(TextField field) {
        String text = field.getText();
        if (text.isEmpty() || !Pattern.matches("[A-Za-z]+\\s?[A-Za-z]*", text)) {
            ChangeListener resetStyle = (observableValue, oldV, newV) -> {
            if ((boolean)newV) {
                field.getStyleClass().clear();
                field.getStyleClass().addAll("text-field", "text-input");
            }};
            field.focusedProperty().addListener(resetStyle);
            return false;
        };
        return true;
    }

    private boolean validatePhone(TextField field) {
        String text = field.getText();
        if (text.isEmpty() || !Pattern.matches("[0-9]{10}", text)) {
            ChangeListener resetStyle = (observableValue, oldV, newV) -> {
                if ((boolean)newV) {
                    field.getStyleClass().clear();
                    field.getStyleClass().addAll("text-field", "text-input");
                }};
            field.focusedProperty().addListener(resetStyle);
            return false;
        }
        return true;
    }

    private boolean validateSeniority(TextField field) {
        try {
            int num = Integer.parseInt(field.getText());
            if (num > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            ChangeListener resetStyle = (observableValue, oldV, newV) -> {
                if ((boolean)newV) {
                    field.getStyleClass().clear();
                    field.getStyleClass().addAll("text-field", "text-input");
                }};
            field.focusedProperty().addListener(resetStyle);
            return false;
        }
        return false;
    }

    private boolean validateHours(TextField field) {
        return false;
    }
}
