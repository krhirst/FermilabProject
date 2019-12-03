package reportPage;

import application.EmployeeList;
import application.FermiConnector;
import application.FermiEntry;
import editUsersPage.EditUsersView;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

public class ReportsController {
    private FermiConnector base = new FermiConnector();

    @FXML
    private TableView<FermiEntry> dataTable;

    @FXML
    TableColumn<FermiEntry, String> firstNameCol, lastNameCol, phoneCol;

    @FXML
    TableColumn<FermiEntry, Double> overCol;

    @FXML
    TableColumn<FermiEntry, Integer> senCol;

    @FXML
    TableColumn<FermiEntry, Boolean> bisonCol;

    @FXML
    private Button printButton, backButton, bisonButton, seniorityButton;
    
    @FXML
    private TextField searchNumber;
    
    @FXML
    private Text errorText;

    public ReportsController() {

    }

    @FXML
    private void initialize() {
        firstNameCol.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory("lastName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory("phone"));
        overCol.setCellValueFactory(new PropertyValueFactory("overtime"));
        senCol.setCellValueFactory(new PropertyValueFactory("seniority"));
        bisonCol.setCellValueFactory(new PropertyValueFactory("inBison"));
    }

    @FXML
    private void printPage() {
        Node root = dataTable;
        PrinterJob job = PrinterJob.createPrinterJob();

        if (job != null && job.showPrintDialog(printButton.getScene().getWindow())) {
            Printer printer = job.getPrinter();
            PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);

            double width = 600;
            double height = 800;

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
    
    @FXML
    private void goBack() throws Exception{
    	Stage stage = (Stage) backButton.getScene().getWindow();

        EditUsersView view = new EditUsersView();
        view.showView(stage);
    }
    
    @FXML
    private void BisonReport() throws Exception{
    	
    	dataTable.getItems().clear();
    	
    	//grabbing list of employees from database
        EmployeeList employees = new EmployeeList();
        ObservableList<FermiEntry> bisonEmployees = FXCollections.observableArrayList();
    	
    	//counter for the current index in employees
    	int emp = 0;

    	Iterator iterator = employees.iterator();
    	while (iterator.hasNext()) {
    	    FermiEntry entry = (FermiEntry) iterator.next();
    	    if (entry.isInBison())
    	        bisonEmployees.add(entry);
        }
    	
    	//bubble sort
    	for (int i = 0; i < bisonEmployees.size()-1; i++)   {   
    		emp = 0;
    	    for (int j = 0; j < bisonEmployees.size()-i-1; j++){
    	    	//compare the last names of the current employee and the next one in the list
    	        if (compare(bisonEmployees.get(j).getLastName(), bisonEmployees.get(j+1).getLastName()) >= 1) { 
    	            swap(bisonEmployees.get(j), bisonEmployees.get(j+1), emp, bisonEmployees);
    	        }
    	        emp++;
    		}
    	}
    	
    	dataTable.setItems(bisonEmployees);
    }
    
    private void swap(FermiEntry fermiEntry, FermiEntry fermiEntry2, int emp, List<FermiEntry> employees) {
		
    	FermiEntry temp = new FermiEntry(fermiEntry.getFirstName(), fermiEntry.getLastName(), fermiEntry.getPhone(), fermiEntry.getOvertime(),
                fermiEntry.getSeniority(), fermiEntry.isInBison());
		
		employees.set(emp, fermiEntry2);
		
		employees.set(emp+1, temp);
		
	}
	
    
    private int compare(String aLastName, String bLastName) {
		return aLastName.compareTo(bLastName);
	}

	@FXML
    private void SeniorReport() throws Exception{
		
		int searchNum;
		errorText.setVisible(false);
		
    	try {
    		searchNum = Integer.parseInt(searchNumber.getText());
    	}
    	catch (NumberFormatException e) {
    		errorText.setVisible(true);
    		searchNumber.getStyleClass().add("error");
            ChangeListener resetStyle = (observableValue, oldV, newV) -> {
                if ((boolean) newV) {
                    searchNumber.getStyleClass().clear();
                    searchNumber.getStyleClass().addAll("text-field", "text-input");
                }
            };
            searchNumber.focusedProperty().addListener(resetStyle);
    		return;
    	}
    	
    	ObservableList<FermiEntry> employees = getEmployees();
    	ObservableList<FermiEntry> SeniorityEmployees = FXCollections.observableArrayList();
    	
    	for(FermiEntry e: employees) {
    		if(e.getSeniority() == searchNum) {
    			SeniorityEmployees.add(e);
    		}
    	}
    	
    	dataTable.setItems(SeniorityEmployees);
    }

	private ObservableList<FermiEntry> getEmployees() {
		
		ObservableList<FermiEntry> employees = FXCollections.observableArrayList();
		
		try {
            Statement stmt = base.getConn().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM hours_offered");
            while (result.next()) {
                employees.add(new FermiEntry(result.getString(1), result.getString(2), result.getString(3), result.getDouble(4),
                        result.getInt(5), result.getBoolean(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
		return employees;
	}
}
