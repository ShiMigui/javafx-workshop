package gui.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class AlertController {
    protected static Alert alert;

    protected static void setContent(AlertType type, String title, String header, String content) {
	if (alert == null)
	    alert = new Alert(type);
	else
	    alert.setAlertType(type);

	alert.setTitle(title);
	alert.setHeaderText(header);
	alert.setContentText(content);
    }

    protected static void setContent(AlertType type, String title, String content) {
	AlertController.setContent(type, title, null, content);
    }
}
