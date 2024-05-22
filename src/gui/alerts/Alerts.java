package gui.alerts;

import javafx.scene.control.Alert.AlertType;

public class Alerts extends AlertController {
    private Alerts() {
    }

    public static void showAlert(AlertType type, String title, String header, String content) {
	Alerts.setContent(type, title, header, content);
	alert.show();
    }

    public static void showAlert(AlertType type, String title, String content) {
	Alerts.showAlert(type, title, null, content);
    }
}
