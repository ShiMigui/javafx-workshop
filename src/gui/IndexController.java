package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.controllers.IController;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class IndexController implements IController {
    @FXML
    private MenuItem itmDepartments;
    @FXML
    private MenuItem itmSellers;
    @FXML
    private MenuItem itmAbout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void onItmDepartmentsAction() {
	System.out.println("Item departments click!");
    }

    @FXML
    private void onItmSellersAction() {
	System.out.println("Item sellers click!");
    }

    @FXML
    private void onItmAboutAction() {
	System.out.println("Item about click!");
    }
}
