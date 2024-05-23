package gui.views;

import java.net.URL;
import java.util.ResourceBundle;

import gui.interfaces.IListController;
import gui.utils.IndexContentManager;
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
    }

    @FXML
    @Override
    public void onBtnNewAction() {
	System.out.println("Btn new click!");
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
