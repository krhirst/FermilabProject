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


public class EditUsersController {

    private FermiConnector db = new FermiConnector();

    @FXML
    private Button addButton;

    @FXML
    private Button homeButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField seniorityField;

    @FXML
    private TextField hoursOfferedField;

    @FXML
    private CheckBox bisonProgramCheckBox;

    @FXML
    private Label result;

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
}
