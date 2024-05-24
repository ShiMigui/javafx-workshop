package gui.views.modal;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.alerts.Alerts;
import gui.elements.ComboList;
import gui.utils.TextfieldConstraint;
import gui.utils.Utils;
import gui.utils.ValidationException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Seller;
import model.services.DepartmentService;
import model.services.ServiceFactory;

public class SellerFormController extends EntityFormController<Seller> {
    private DepartmentService departments;

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
    private ComboBox<Department> cmbDepartment;
    @FXML
    private Button btnDone;
    @FXML
    private Button btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	departments = ServiceFactory.getDepartmentService();

	nodes();
    }

    private void nodes() {
	TextfieldConstraint.asNumberInput(txfId);
	TextfieldConstraint.asDecimalInput(txfSalary);
	TextfieldConstraint.maxLengthInput(txfName, 255);
	TextfieldConstraint.maxLengthInput(txfEmail, 320);
	Utils.formatDatePicker(txfBirthDate, "dd/MM/yyyy");

	Callback<ListView<Department>, ListCell<Department>> factory = param -> new ComboList<Department>();
	ObservableList<Department> obs = FXCollections.observableArrayList(departments.findAll());

	cmbDepartment.setItems(obs);
	cmbDepartment.setCellFactory(factory);
	cmbDepartment.setButtonCell(factory.call(null));
    }

    @Override
    protected void loadForm() {
	super.loadForm();

	Seller obj = this.getAuxEntity();
	String salary = "0.0";
	String name = obj.getName();
	String email = obj.getEmail();
	Date birthDate = obj.getBirthDate();
	Department department = obj.getDepartment();

	if (obj.getSalary() != null) {
	    Locale.setDefault(Locale.US);
	    salary = String.format("%.2f", obj.getSalary());
	}

	txfName.setText(name);
	txfEmail.setText(email);
	txfSalary.setText(salary);
	txfBirthDate.setValue(LocalDate.ofInstant(birthDate.toInstant(), ZoneId.systemDefault()));

	if (department != null)
	    cmbDepartment.setValue(department);
	else
	    cmbDepartment.getSelectionModel().selectFirst();
    }

    @FXML
    @Override
    protected void onBtnDoneAction() {
	try {
	    updateEntity();

	    getService().insertOrUpdate(getAuxEntity());
	    notifyDataChanged();
	    close();
	} catch (ValidationException e) {
	    Alerts.showAlert(AlertType.ERROR, "Error", e.getMessage());
	} catch (RuntimeException e) {
	    Alerts.showAlert(AlertType.ERROR, "Unexpected Error", e.getMessage());
	}
    }

    private void updateEntity() throws ValidationException {
	Locale.setDefault(Locale.US);
	String name = txfName.getText();
	String email = txfEmail.getText();
	String salaryText = txfSalary.getText();
	Double salary = null;
	Department dep = cmbDepartment.getValue();
	LocalDate localDate = txfBirthDate.getValue();

	if (name == null || name.trim().equals("")) {
	    txfName.requestFocus();
	    throw new ValidationException("Name cannot be empty!");
	}

	if (email == null || email.trim().equals("")) {
	    txfEmail.requestFocus();
	    throw new ValidationException("Email cannot be empty!");
	}

	if (!email.contains("@")) {
	    txfEmail.requestFocus();
	    throw new ValidationException("Email not valid!");
	}

	if (salaryText == null || salaryText.trim().equals("")) {
	    txfSalary.requestFocus();
	    throw new ValidationException("Salary cannot be empty!");
	}

	try {
	    salary = Double.parseDouble(salaryText);
	} catch (NumberFormatException e) {
	    txfSalary.requestFocus();
	    throw new ValidationException("Salary must be a double number!");
	}

	if (localDate == null) {
	    txfBirthDate.requestFocus();
	    throw new ValidationException("Birth date cannot be empty!");
	}

	Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
	Date date = Date.from(instant);

	Seller obj = getAuxEntity();
	obj.setName(name);
	obj.setEmail(email);
	obj.setSalary(salary);
	obj.setDepartment(dep);
	obj.setBirthDate(date);
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
