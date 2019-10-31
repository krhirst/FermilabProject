package xmlstuff;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test {


		
public void start (Stage stage) throws IOException{
	FXMLLoader loader = new FXMLLoader();
	String fxmlDocPath = "user.fxml";
	FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
	
	TitledPane root = (TitledPane) loader.load(fxmlStream);
	Scene scene = new Scene(root);
	stage.setScene(scene);
	stage.setTitle("User");
	stage.show();

}
}