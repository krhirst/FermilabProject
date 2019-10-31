package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UserView {
    @FXML
    private TableView<FermiEntry> dataTable;
    private FermiConnector base = new FermiConnector();

    public UserView() {
    }

    public void showView(Stage window) throws Exception {
        Pane root = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("application/userfc.fxml"));
        Scene scene = new Scene(root, 650, 600);
        TableView<FermiEntry> tb = (TableView<FermiEntry>) root.getChildren().get(0);
        scene.getStylesheets().add(getClass().getResource("defaultStyle.css").toExternalForm());
        window.setTitle("User");
        window.setScene(scene);
        window.setResizable(false);
        window.show();
    }
}
