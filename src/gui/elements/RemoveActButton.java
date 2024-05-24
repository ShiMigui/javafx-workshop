package gui.elements;

import java.util.function.Consumer;

import model.interfaces.IEntity;

public class RemoveActButton<E extends IEntity> extends ActButton<E> {
    public RemoveActButton(Consumer<E> act) {
	super(act);
    }

    @Override
    public String getTextButton() {
	return "remove";
    }
}
