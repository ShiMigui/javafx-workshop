package gui.utils;

import java.io.IOException;
import java.util.function.Consumer;

import gui.alerts.Alerts;
import gui.interfaces.IController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowManager {
    private ViewLoader loader = new ViewLoader();
    private Stage stage;

    public void window(String relative, String name, Stage owner, Consumer<Stage> config) {
	try {
	    if(stage == null)
		stage = new Stage();
	    
	    loader.getResource(relative);
	    Parent node = loader.load();

	    stage.setTitle(name);
	    stage.initOwner(owner);
	    stage.setScene(new Scene(node));

	    config.accept(stage);
	} catch (IOException e) {
	    Alerts.showAlert(AlertType.ERROR, "Cannot load resource", e.getMessage());
	}
    }

    public void windowModal(String relative, String name) {
	window(relative, name, IndexContentManager.getStage(), (stage) -> {
	    stage.setResizable(false);
	    stage.initModality(Modality.WINDOW_MODAL);
	});
    }

    public <C extends IController> C getController() {
	return loader.getLoader().getController();
    }

    public Stage getWindow() {
	return stage;
    }

    public void showAndWait() {
	stage.showAndWait();
    }
}
