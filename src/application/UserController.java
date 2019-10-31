package application;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class UserController {

    private FermiConnector base = new FermiConnector();

    @FXML
    private TableView<FermiEntry> dataTable;

    @FXML
    TableColumn<FermiEntry, String> nameCol;

    @FXML
    TableColumn<FermiEntry, String> phoneCol;

    @FXML
    TableColumn<FermiEntry, String> overCol;

    @FXML
    TableColumn<FermiEntry, String> senCol;

    @FXML
    TableColumn<FermiEntry, String> bisonCol;

    public UserController() throws SQLException {
    }

    @FXML
    private void initialize() throws SQLException {
    }

    public TableView<FermiEntry> createTable() throws SQLException {

        dataTable = new TableView<>();
        dataTable.setItems(base.getData());
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        overCol.setCellValueFactory(new PropertyValueFactory("overtime"));
        senCol.setCellValueFactory(new PropertyValueFactory("seniority"));
        bisonCol.setCellValueFactory(new PropertyValueFactory("inBison"));

        dataTable.getColumns().addAll(nameCol, overCol, senCol, bisonCol);


        return dataTable;
    }
}
