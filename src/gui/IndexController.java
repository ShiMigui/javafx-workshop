package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.interfaces.IController;
import gui.utils.IndexContentManager;
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
	IndexContentManager.loadContent("/gui/views/DepartmentListView.fxml", "Department's list");
    }

    @FXML
    private void onItmSellersAction() {
	IndexContentManager.loadContent("/gui/views/SellerListView.fxml", "Seller's list");
    }

    @FXML
    private void onItmAboutAction() {
	System.out.println("Item about click!");
    }
}
