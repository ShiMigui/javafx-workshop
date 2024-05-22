package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public synchronized void start(Stage stage) {
	try {
	    ScrollPane node = FXMLLoader.load(getClass().getResource("/gui/IndexView.fxml"));
	    
	    node.setFitToHeight(true);
	    node.setFitToWidth(true);
	    
	    Scene scene = new Scene(node);
	    stage.setScene(scene);
	    stage.show();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public static void main(String[] args) {
	launch(args);
    }
}
