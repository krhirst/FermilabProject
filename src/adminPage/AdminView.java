package adminPage;

import application.FermiEntry;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminView {
    @FXML
    private TableView<FermiEntry> dataTable;

    public AdminView() {
    }

    public void showView(Stage window) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("adminPage.fxml"));
        Scene scene = new Scene(root, 650, 600);
        window.setTitle("Administrator");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
