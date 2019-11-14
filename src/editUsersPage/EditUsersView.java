package editUsersPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class EditUsersView {

    public EditUsersView() {

    }

    public void showView(Stage window) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("editUsers.fxml"));
        Scene scene = new Scene(root, 650, 600);
        window.setTitle("Edit Users");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
