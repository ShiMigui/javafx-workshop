package gui.views;

import java.net.URL;
import java.util.ResourceBundle;

import gui.controllers.IController;
import gui.utils.IndexContentManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Department;
import model.interfaces.IEntityService;
import model.services.ServiceFactory;

public class DepartmentListController implements IController {
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

    private void updateTable() {
	if (service == null)
	    throw new NullPointerException("Service was null!");

	table.setItems(FXCollections.observableArrayList(service.findAll()));
    }

    private void intializeNodes() {
	this.tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	this.tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

	table.prefHeightProperty().bind(IndexContentManager.getStage().heightProperty());
    }

    @FXML
    private void onBtnNewAction() {
	System.out.println("Btn new click!");
    }
}
