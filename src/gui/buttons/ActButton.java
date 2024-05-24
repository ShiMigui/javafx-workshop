package gui.buttons;

import java.util.function.Consumer;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import model.interfaces.IEntity;

public abstract class ActButton<E extends IEntity> extends TableCell<E, E> {
    private final Button button = new Button(getTextButton());
    private Consumer<E> act;

    public ActButton(Consumer<E> act) {
	this.act = act;
    }

    @Override
    protected void updateItem(E obj, boolean empty) {
	super.updateItem(obj, empty);
	if (obj == null) {
	    setGraphic(null);
	    return;
	}
	setGraphic(button);
	button.setOnAction(event -> act.accept(obj));
    }

    public abstract String getTextButton();
}
