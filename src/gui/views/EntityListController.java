package gui.views;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import gui.alerts.Alerts;
import gui.alerts.Confirmations;
import gui.elements.EditActButton;
import gui.elements.RemoveActButton;
import gui.interfaces.IDataChangedController;
import gui.utils.IndexContentManager;
import gui.utils.WindowManager;
import gui.views.modal.EntityFormController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.interfaces.IEntity;
import model.interfaces.IEntityService;

public abstract class EntityListController<E extends IEntity> implements IDataChangedController {
    private IEntityService<E> service;
    protected E entityBtnNew;
    private WindowManager wm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	wm = new WindowManager();
	intializeNodes();
    }

    protected void updateTable() {
	getTable().setItems(FXCollections.observableArrayList(service.findAll()));
	this.addRemoveButtons(getRemoveColumn());
	this.addEditButtons(getEditColumn());
    }

    protected void intializeNodes() {
	getTable().prefHeightProperty().bind(IndexContentManager.getStage().heightProperty());
    }

    protected void setService(IEntityService<E> service) {
	this.service = service;
	updateTable();
    }

    protected void loadFormWindowLikeConsumer(E obj) {
	loadFormWindow(obj, "Edit entity");
    }
    
    protected void loadFormWindow(E obj, String windowName) {
	try {
	    if (wm.getWindow() == null)
		wm.windowModal(getRelativeFormView(), windowName);

	    EntityFormController<E> controller = wm.getController();
	    controller.setService(service);
	    controller.setMyStage(wm.getWindow());
	    controller.setDataChanged(this);
	    controller.setAuxEntity(obj);

	    wm.showAndWait();
	} catch (RuntimeException e) {
	    Alerts.showAlert(AlertType.ERROR, "Cannot load resources", e.getMessage());
	    e.printStackTrace();
	}
    }

    protected void addRemoveButtons(TableColumn<E, E> column) {
	setCellValueFactory(column);
	column.setCellFactory(param -> new RemoveActButton<E>(this::removeEntity));
    }
    
    protected void addEditButtons(TableColumn<E, E> column) {
	setCellValueFactory(column);
	column.setCellFactory(param -> new EditActButton<E>(this::loadFormWindowLikeConsumer));
    }

    protected void setCellValueFactory(TableColumn<E, E> column) {
	column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    }

    protected void removeEntity(E obj) {
	try {
	    Optional<ButtonType> resp = Confirmations.showAlert("Remove entity",
		    "Do you really want to remove \"" + obj.getName() + "\"?");

	    if (resp.get() == ButtonType.OK)
		service.remove(obj);
	    
	    this.updateTable();
	} catch (RuntimeException e) {
	    Alerts.showAlert(AlertType.ERROR, "Cannot remove \"" + obj.getName() + "\"", e.getMessage());
	}
    }

    protected abstract String getRelativeFormView();

    protected abstract TableView<E> getTable();

    protected abstract void onBtnNewAction();

    @Override
    public void onDataChanged() {
	updateTable();
    }

    protected abstract TableColumn<E, E> getRemoveColumn();

    protected abstract TableColumn<E, E> getEditColumn();
}
