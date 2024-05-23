package gui.interfaces;

import javafx.collections.FXCollections;
import javafx.scene.control.TableView;
import model.interfaces.IEntity;
import model.interfaces.IEntityService;

public interface IListController<E extends IEntity> extends IController {
    void intializeNodes();

    default void updateTable() {
	this.getTable().setItems(FXCollections.observableArrayList(getService().findAll()));
    }

    void loadFormWindow(E obj, String windowName);

    void onBtnNewAction();

    IEntityService<E> getService();

    TableView<E> getTable();
}
