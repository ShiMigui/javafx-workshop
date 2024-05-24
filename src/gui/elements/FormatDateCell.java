package gui.elements;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.TableCell;
import model.interfaces.IEntity;

public class FormatDateCell<E extends IEntity> extends TableCell<E, Date> {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormatDateCell() {
    }

    @Override
    protected void updateItem(Date item, boolean empty) {
	super.updateItem(item, empty);
	if (empty) {
	    setText(null);
	} else {
	    setText(sdf.format(item));
	}
    }
}
