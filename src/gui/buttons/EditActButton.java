package gui.buttons;

import java.util.function.Consumer;

import model.interfaces.IEntity;

public class EditActButton<E extends IEntity> extends ActButton<E> {
    public EditActButton(Consumer<E> act) {
	super(act);
    }

    @Override
    public String getTextButton() {
	return "edit";
    }
}
