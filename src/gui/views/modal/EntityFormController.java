package gui.views.modal;

import java.net.URL;
import java.util.ResourceBundle;

import gui.interfaces.IController;
import gui.interfaces.IDataChangedController;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.interfaces.IEntity;
import model.interfaces.IEntityService;

public abstract class EntityFormController<E extends IEntity> implements IController {
    private E auxEntity;
    private IEntityService<E> service;
    private IDataChangedController dataChanged;
    private Stage myStage;

    @Override
    public abstract void initialize(URL url, ResourceBundle rb);

    protected abstract void onBtnDoneAction();

    protected abstract void onBtnCancelAction();

    protected abstract TextField getTxfId();

    protected void loadForm() {
	Integer id = this.getAuxEntity().getId();
	if (id == null)
	    getTxfId().setText("");
	else
	    getTxfId().setText(id.toString());
    }

    protected void close() {
	myStage.close();
    }

    protected void notifyDataChanged() {
	this.dataChanged.onDataChanged();
    }

    protected E getAuxEntity() {
	return auxEntity;
    }

    protected IEntityService<E> getService() {
	return service;
    }

    public void setAuxEntity(E ref) {
	if (ref == null)
	    throw new NullPointerException("Reference entity was null!");

	auxEntity = ref;
	loadForm();
    }

    public void setDataChanged(IDataChangedController dataChanged) {
	if (dataChanged == null)
	    throw new NullPointerException("DataChangedController was null!");

	this.dataChanged = dataChanged;
    }

    public IDataChangedController getDataChanged() {
	return dataChanged;
    }

    public void setMyStage(Stage myStage) {
	if (myStage == null)
	    throw new NullPointerException("Stage was null!");

	this.myStage = myStage;
    }

    public void setService(IEntityService<E> service) {
	if (service == null)
	    throw new NullPointerException("Service was null!");

	this.service = service;
    }
}
