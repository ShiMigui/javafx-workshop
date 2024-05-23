package gui.views;

import java.net.URL;
import java.util.ResourceBundle;

import gui.alerts.Alerts;
import gui.interfaces.IDataChangedController;
import gui.utils.IndexContentManager;
import gui.utils.WindowManager;
import gui.views.modal.EntityFormController;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import model.interfaces.IEntity;
import model.interfaces.IEntityService;

public abstract class EntityListController<E extends IEntity> implements IDataChangedController {
    private IEntityService<E> service;
    protected E auxEntityBtnNew;
    private WindowManager wm;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
	wm = new WindowManager();
	intializeNodes();
    }

    protected void updateTable() {
	getTable().setItems(FXCollections.observableArrayList(service.findAll()));
    }

    protected void intializeNodes() {
	getTable().prefHeightProperty().bind(IndexContentManager.getStage().heightProperty());
    }

    protected void setService(IEntityService<E> service) {
	this.service = service;
	updateTable();
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
	}
    }

    protected abstract String getRelativeFormView();

    protected abstract TableView<E> getTable();

    protected abstract void onBtnNewAction();

    @Override
    public void onDataChanged() {
	updateTable();
    }
}
