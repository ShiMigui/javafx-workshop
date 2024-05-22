package gui.alerts;

import java.util.Optional;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public final class Confirmations extends AlertController {
    private Confirmations() {
    }
    
    public static Optional<ButtonType> showAlert(String title, String header, String content) {
	Confirmations.setContent(AlertType.CONFIRMATION, title, header, content);
	return alert.showAndWait();
    }

    public static Optional<ButtonType> showAlert(String title, String content) {
	return Confirmations.showAlert(title, null, content);
    }
}
