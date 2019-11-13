package login;

import application.FermiConnector;
import application.FermiEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import userPage.UserView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private FermiConnector base = new FermiConnector();

    @FXML
    private Button signInButton;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    public LoginController() {
    }

    @FXML
    private void initialize(){
    }

    @FXML
    private void validateLogin() {
        String username = this.username.getText();
        String password = this.password.getText();
        ArrayList<Login> logins = getData();
        boolean loginExists = false;
        for (Login login : logins) {
            if (login.getUsername().equals(username) && login.getPassword().equals(password)) {
                loginExists = true;
            }
        }
        if (loginExists) {
            try {
                showUserPage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Incorrect username or password");
        }
    }

    @FXML
    private void showUserPage() throws Exception {
        Stage stage = (Stage) signInButton.getScene().getWindow();

        UserView view = new UserView();
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
}
