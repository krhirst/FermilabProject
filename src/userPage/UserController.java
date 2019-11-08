package userPage;

import application.FermiConnector;
import application.FermiEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    private FermiConnector base = new FermiConnector();

    @FXML
    private TableView<FermiEntry> dataTable;

    @FXML
    TableColumn<FermiEntry, String> nameCol;

    @FXML
    TableColumn<FermiEntry, String> phoneCol;

    @FXML
    TableColumn<FermiEntry, Double> overCol;

    @FXML
    TableColumn<FermiEntry, Integer> senCol;

    @FXML
    TableColumn<FermiEntry, Boolean> bisonCol;

    public UserController() throws SQLException {
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@FXML
    private void initialize() throws SQLException {
        nameCol.setCellValueFactory(new PropertyValueFactory("name"));
        overCol.setCellValueFactory(new PropertyValueFactory("overtime"));
        senCol.setCellValueFactory(new PropertyValueFactory("seniority"));
        bisonCol.setCellValueFactory(new PropertyValueFactory("inBison"));

        dataTable.setItems(getData());
    }

    private ObservableList<FermiEntry> getData() {
        ObservableList<FermiEntry> data = FXCollections.observableArrayList();

        try {
            Statement stmt = base.getConn().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM hours_offered");
            while (result.next()) {
                data.add(new FermiEntry(result.getString(1), result.getString(2), result.getDouble(3),
                        result.getInt(4), result.getBoolean(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }

}
