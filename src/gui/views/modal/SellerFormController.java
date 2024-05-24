package gui.views.modal;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.alerts.Alerts;
import gui.utils.TextfieldConstraint;
import gui.utils.Utils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.entities.Seller;

public class SellerFormController extends EntityFormController<Seller> {
    @FXML
    private TextField txfId;
    @FXML
    private TextField txfName;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfSalary;
    @FXML
    private DatePicker txfBirthDate;
    @FXML
    private Button btnDone;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	TextfieldConstraint.asNumberInput(txfId);
	TextfieldConstraint.asDecimalInput(txfSalary);
	TextfieldConstraint.maxLengthInput(txfName, 255);
	TextfieldConstraint.maxLengthInput(txfEmail, 320);
	Utils.formatDatePicker(txfBirthDate, "dd/MM/yyyy");
    }

    @Override
    protected void loadForm() {
	super.loadForm();

	Seller obj = this.getAuxEntity();
	String salary = "0.0";
	String name = obj.getName();
	String email = obj.getEmail();
	Date birthDate = obj.getBirthDate();

	if (obj.getSalary() != null) {
	    Locale.setDefault(Locale.US);
	    salary = String.format("%.2f", obj.getSalary());
	}

	if (birthDate == null) {
	    birthDate = new Date();
	    obj.setBirthDate(birthDate);
	}

	txfName.setText(name);
	txfEmail.setText(email);
	txfSalary.setText(salary);
	txfBirthDate.setValue(LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault()));
    }

    @FXML
    @Override
    protected void onBtnDoneAction() {
	try {
	    String name = txfName.getText();

	    if (name == null || name.trim().equals("")) {
		txfName.requestFocus();
		throw new NullPointerException("Name cannot be null or empty!");
	    }

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
