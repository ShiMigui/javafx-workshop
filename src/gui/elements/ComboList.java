package gui.elements;

import javafx.scene.control.ListCell;
import model.interfaces.IEntity;

public class ComboList<E extends IEntity> extends ListCell<E> {
    @Override
    protected void updateItem(E item, boolean empty) {
	super.updateItem(item, empty);
	this.setText(empty ? "" : item.getName());
    }
}
