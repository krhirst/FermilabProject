package login;

import userPage.UserView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button signInButton;

    public LoginController() {
    }

    @FXML
    private void initialize(){
    }

    @FXML
    private void showUserPage() throws Exception {
        Stage stage = (Stage) signInButton.getScene().getWindow();

        UserView view = new UserView();
        view.showView(stage);
    }
}
