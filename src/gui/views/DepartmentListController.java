package gui.views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;
import model.services.ServiceFactory;

public class DepartmentListController extends EntityListController<Department> {
    @FXML
    private Button btnNew;
    @FXML
    private TableView<Department> table;
    @FXML
    private TableColumn<Department, Integer> tbColumnId;
    @FXML
    private TableColumn<Department, String> tbColumnName;
    @FXML
    private TableColumn<Department, Department> tbColumnEdit;
    @FXML
    private TableColumn<Department, Department> tbColumnRemove;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	super.initialize(url, rb);
	this.setService(ServiceFactory.getDepartmentService());
    }

    @Override
    public void intializeNodes() {
	super.intializeNodes();
	tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    protected String getRelativeFormView() {
	return "/gui/views/modal/DepartmentFormView.fxml";
    }

    @FXML
    @Override
    protected void onBtnNewAction() {
	if (auxEntityBtnNew == null) {
	    auxEntityBtnNew = new Department();
	} else {
	    auxEntityBtnNew.setId(null);
	    auxEntityBtnNew.setName("");
	}

	this.loadFormWindow(auxEntityBtnNew, "New Department");
    }

    @Override
    protected TableView<Department> getTable() {
	return table;
    }
}
