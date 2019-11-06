package userPage;

import application.FermiConnector;
import application.FermiEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Scale;

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

    @FXML
    Button printButton;

    public UserController() throws SQLException {
    }

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

    @FXML
    private void printPage() {
        Pane root = (Pane) printButton.getParent().getParent();
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(printButton.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

            double width = 1000;
            double height = 1200;

            PrintResolution resolution = job.getJobSettings().getPrintResolution();

            width /= resolution.getFeedResolution();

            height /= resolution.getCrossFeedResolution();

            double scaleX = pageLayout.getPrintableWidth() / width / 600;
            double scaleY = pageLayout.getPrintableHeight() / height / 600;

            Scale scale = new Scale(scaleX, scaleY);

            root.getTransforms().add(scale);

            boolean success = job.printPage(pageLayout, root);
            if (success) {
                job.endJob();
            }

            root.getTransforms().remove(scale);
        }
    }

}
