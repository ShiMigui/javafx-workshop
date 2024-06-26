package gui.views;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import gui.elements.FormatDateCell;
import gui.elements.FormatDoubleCell;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entities.Seller;
import model.services.ServiceFactory;

public class SellerListController extends EntityListController<Seller> {
    @FXML
    private Button btnNew;
    @FXML
    private TableView<Seller> table;
    @FXML
    private TableColumn<Seller, Integer> tbColumnId;
    @FXML
    private TableColumn<Seller, String> tbColumnName;
    @FXML
    private TableColumn<Seller, String> tbColumnEmail;
    @FXML
    private TableColumn<Seller, Date> tbColumnBirthDate;
    @FXML
    private TableColumn<Seller, Double> tbColumnSalary;
    @FXML
    private TableColumn<Seller, Seller> tbColumnEdit;
    @FXML
    private TableColumn<Seller, Seller> tbColumnRemove;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	super.initialize(url, rb);
	this.setService(ServiceFactory.getSellerService());
    }

    @Override
    public void intializeNodes() {
	super.intializeNodes();
	tbColumnSalary.setCellFactory(param -> new FormatDoubleCell<>());
	tbColumnBirthDate.setCellFactory(param -> new FormatDateCell<>());

	tbColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
	tbColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
	tbColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	tbColumnSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
	tbColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
    }

    protected String getRelativeFormView() {
	return "/gui/views/modal/SellerFormView.fxml";
    }

    @FXML
    @Override
    protected void onBtnNewAction() {
	if (entityBtnNew == null) {
	    entityBtnNew = new Seller();
	    entityBtnNew.setBirthDate(new Date());
	} else {
	    entityBtnNew.setId(null);
	    entityBtnNew.setName("");
	    entityBtnNew.setEmail("");
	    entityBtnNew.setSalary(0.0);
	}

	this.loadFormWindow(entityBtnNew, "New seller");
    }

    @Override
    protected TableView<Seller> getTable() {
	return table;
    }

    @Override
    protected TableColumn<Seller, Seller> getRemoveColumn() {
	return this.tbColumnRemove;
    }

    @Override
    protected TableColumn<Seller, Seller> getEditColumn() {
	return this.tbColumnEdit;
    }
}
