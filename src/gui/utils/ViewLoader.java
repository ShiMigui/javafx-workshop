package gui.utils;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class ViewLoader {
    private FXMLLoader loader;

    public void getResource(String relative) {
	URL url = IndexContentManager.class.getResource(relative);
	loader = new FXMLLoader(url);
    }

    public FXMLLoader getLoader() {
	return loader;
    }

    public <V extends Node> V load() throws IOException {
	return loader.load();
    }
}
