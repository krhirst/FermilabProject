package userPage;

import application.FermiConnector;
import application.FermiEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class UserView {
    @FXML
    private TableView<FermiEntry> dataTable;

    public UserView() {
    }

    public void showView(Stage window) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("../userPage/userfc.fxml"));
        Scene scene = new Scene(root, 650, 600);
        window.setTitle("User");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
