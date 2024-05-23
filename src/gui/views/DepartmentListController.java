package gui.views;

import java.net.URL;
import java.util.ResourceBundle;

import gui.interfaces.IListController;
import gui.utils.IndexContentManager;
import gui.utils.WindowManager;
import gui.views.modal.EntityFormController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;
import model.interfaces.IEntityService;
import model.services.ServiceFactory;

public class DepartmentListController implements IListController<Department> {
    private IEntityService<Department> service;
    private WindowManager wm;
    private Department auxDepartmentBtnNew;

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
	service = ServiceFactory.getDepartmentService();
	wm = new WindowManager();
	intializeNodes();
	updateTable();
    }

    @Override
    public void intializeNodes() {
	this.tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	this.tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

	table.prefHeightProperty().bind(IndexContentManager.getStage().heightProperty());
    }

    @Override
    public void loadFormWindow(Department obj, String windowName) {
	try {
	    if (wm.getWindow() == null)
		wm.windowModal("/gui/views/modal/DepartmentFormView.fxml", windowName);

	    EntityFormController<Department> controller = wm.getController();
	    controller.setService(this.getService());
	    controller.setMyStage(wm.getWindow());
	    controller.setDataChanged(this);
	    controller.setAuxEntity(obj);

	    wm.showAndWait();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    System.out.println(e.getMessage());
	}
    }

    @FXML
    @Override
    public void onBtnNewAction() {
	if (auxDepartmentBtnNew == null) {
	    auxDepartmentBtnNew = new Department();
	} else {
	    auxDepartmentBtnNew.setId(null);
	    auxDepartmentBtnNew.setName("");
	}

	this.loadFormWindow(auxDepartmentBtnNew, "New Department");
    }

    @Override
    public IEntityService<Department> getService() {
	if (service == null)
	    throw new NullPointerException("Service was null!");

	return service;
    }

    @Override
    public TableView<Department> getTable() {
	return table;
    }

    @Override
    public void onDataChanged() {
	updateTable();
    }
}
