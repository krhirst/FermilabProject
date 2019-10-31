package login;

import application.UserView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
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
//        FXMLLoader loader = new FXMLLoader();
//        String fxmlDocPath = "userfc.fxml";
//        Pane root = (Pane) FXMLLoader.load(getClass().getResource("../../../src/application/userfc.fxml"));
//        Scene scene = new Scene(root, 650, 600);
//        scene.getStylesheets().add(getClass().getResource("defaultStyle.css").toExternalForm());
//        primaryStage.setTitle("User");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
    }
}
