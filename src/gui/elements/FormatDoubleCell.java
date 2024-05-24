package gui.elements;

import java.util.Locale;

import javafx.scene.control.TableCell;
import model.interfaces.IEntity;

public class FormatDoubleCell<E extends IEntity> extends TableCell<E, Double> {
    public FormatDoubleCell() {
    }

    @Override
    protected void updateItem(Double item, boolean empty) {
	super.updateItem(item, empty);
	if (empty) {
	    setText(null);
	} else {
	    Locale.setDefault(Locale.US);
	    setText(String.format("%." + 2 + "f", item));
	}
    }
}
