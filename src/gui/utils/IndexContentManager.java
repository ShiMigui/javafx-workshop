package gui.utils;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IndexContentManager {
    private static Stage stage;
    private static VBox indexVbox;
    private static Node indexMenu;
    private static ViewLoader loader = new ViewLoader();

    private IndexContentManager() {
    }

    public static void loadContent(String relative, String name) {
	try {
	    loader.getResource(relative);
	    VBox content = loader.load();

	    indexVbox.getChildren().clear();
	    indexVbox.getChildren().add(indexMenu);
	    indexVbox.getChildren().addAll(content.getChildren());

	    stage.setTitle(name);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static void setStage(Stage stage) {
	if (IndexContentManager.stage != null)
	    throw new RuntimeException("Stage was not null!");

	try {
	    IndexContentManager.stage = stage;

	    loader.getResource("/gui/IndexView.fxml");
	    ScrollPane node = loader.load();

	    node.setFitToHeight(true);
	    node.setFitToWidth(true);
	    indexVbox = (VBox) node.getContent();
	    indexMenu = indexVbox.getChildren().get(0);

	    stage.setScene(new Scene(node));
	    stage.setTitle("Bem-vindo(a)");
	    stage.show();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public static Stage getStage() {
	return stage;
    }

    public static Scene getScene() {
	return stage.getScene();
    }

    public static ViewLoader getLoader() {
	return loader;
    }
}
