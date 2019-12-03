package login;

import adminPage.AdminView;
import application.FermiConnector;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import userPage.UserView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginController {

	private FermiConnector db = new FermiConnector();

	@FXML
	private Button signInButton;

	@FXML
	private TextField username;

	@FXML
	private PasswordField password;

	@FXML
	private Label validationError;

	public LoginController() {
	}

	@FXML
	private void initialize() {
		validationError.setVisible(false);
		signInButton.setDefaultButton(true);
	}

	@FXML
	private void validateLogin() {
		String username = this.username.getText();
		String password = this.password.getText();
		Login user = null;
		ArrayList<Login> logins = Login.getLogins(db);
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

	private void showValidationError() {
		username.getStyleClass().add("error");
		password.getStyleClass().add("error");
		password.clear();
		validationError.setVisible(true);

		ChangeListener resetStyle = (observableValue, oldV, newV) -> {
			if ((boolean) newV) {
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

	public void showView(Stage window) throws Exception {
		Pane root = (Pane) FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
		window.setTitle("Log In");
		window.setScene(scene);
		window.setResizable(false);
		window.show();
	}

}
