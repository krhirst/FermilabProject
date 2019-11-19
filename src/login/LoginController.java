package login;

import adminPage.AdminView;
import application.FermiConnector;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import userPage.UserView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LoginController {

    private FermiConnector base = new FermiConnector();

    @FXML
    private Button signInButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label validationError;

    public LoginController() {
    }

    @FXML
    private void initialize() {
        validationError.setVisible(false);
    }

    @FXML
    private void validateLogin() {
        String username = this.username.getText();
        String password = this.password.getText();
        Login user = null;
        ArrayList<Login> logins = getData();
        boolean loginExists = false;
        for (Login login : logins) {
            if (login.getUsername().equals(username) && login.getPassword().equals(password)) {
                loginExists = true;
                user = login;
            }
        }
        if (loginExists) {
            try {
                if (user.isAdmin()) {
                    showAdminPage();
                } else {
                    showUserPage();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showValidationError();
        }
    }

    @FXML
    private void showUserPage() throws Exception {
        Stage stage = (Stage) signInButton.getScene().getWindow();

        UserView view = new UserView();
        view.showView(stage);
    }

    @FXML
    private void showAdminPage() throws Exception {
        Stage stage = (Stage) signInButton.getScene().getWindow();

        AdminView view = new AdminView();
        view.showView(stage);
    }

    private ArrayList<Login> getData() {
        ArrayList<Login> data = new ArrayList<>();
        try {
            Statement stmt = base.getConn().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM logins");
            while (result.next()) {
                data.add(new Login(result.getInt(1), result.getString(2), result.getString(3),
                        result.getBoolean(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

    private void showValidationError() {
        username.getStyleClass().add("error");
        password.getStyleClass().add("error");
        validationError.setVisible(true);

        ChangeListener resetStyle = (observableValue, oldV, newV) -> {
            if ((boolean)newV) {
                username.getStyleClass().clear();
                password.getStyleClass().clear();
                username.getStyleClass().addAll("text-field", "text-input");
                password.getStyleClass().addAll("text-field", "text-input");
                validationError.setVisible(false);
            }
        };

        username.focusedProperty().addListener(resetStyle);
        password.focusedProperty().addListener(resetStyle);
    }
}
