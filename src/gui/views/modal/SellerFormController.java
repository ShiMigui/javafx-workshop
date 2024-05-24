package gui.views.modal;

import java.net.URL;
import java.util.ResourceBundle;

import gui.alerts.Alerts;
import gui.utils.TextfieldConstraint;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Seller;

public class SellerFormController extends EntityFormController<Seller> {
    @FXML
    private TextField txfId;
    @FXML
    private TextField txfName;
    @FXML
    private Button btnDone;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	TextfieldConstraint.maxLengthInput(txfName, 255);
	TextfieldConstraint.asNumberInput(txfId);
    }

    @Override
    protected void loadForm() {
	super.loadForm();

	Seller dep = this.getAuxEntity();
	txfName.setText(dep.getName());
    }

    @FXML
    @Override
    protected void onBtnDoneAction() {
	try {
	    String name = txfName.getText();

	    if (name == null || name.trim().equals(""))
		throw new NullPointerException("Name cannot be null or empty!");

	    Seller obj = getAuxEntity();
	    obj.setName(name);

	    getService().insertOrUpdate(obj);
	    notifyDataChanged();
	    close();
	} catch (NullPointerException e) {
	    Alerts.showAlert(AlertType.ERROR, "Error", e.getMessage());
	} catch (RuntimeException e) {
	    Alerts.showAlert(AlertType.ERROR, "Unexpected Error", e.getMessage());
	}
    }

    @FXML
    @Override
    protected void onBtnCancelAction() {
	this.close();
    }

    @Override
    protected TextField getTxfId() {
	return txfId;
    }
}
