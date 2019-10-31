package xmlstuff;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	FXMLLoader loader = new FXMLLoader();
    	String fxmlDocPath = "userfc.fxml";
        Pane root = (Pane) FXMLLoader.load(getClass().getResource("userfc.fxml")); 
        Scene scene = new Scene(root, 650, 600);
        scene.getStylesheets().add(getClass().getResource("defaultStyle.css").toExternalForm());
        primaryStage.setTitle("User");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
