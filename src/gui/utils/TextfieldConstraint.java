package gui.utils;

import java.util.function.Predicate;

import javafx.scene.control.TextField;

public final class TextfieldConstraint {
    private TextfieldConstraint() {
    }

    public static void asNumberInput(TextField txf) {
	addAConstraint(txf, (newV) -> newV.matches("\\d*"));
    }

    public static void asDecimalInput(TextField txf) {
	addAConstraint(txf, (newV) -> newV.matches("\\d*([\\.]\\d*)?*"));
    }

    public static void maxLengthInput(TextField txf, Integer max) {
	addAConstraint(txf, (newV) -> newV.length() < max);
    }

    public static void addAConstraint(TextField txf, Predicate<String> constraint) {
	txf.textProperty().addListener((obs, oldv, newv) -> {
	    if (newv != null && !constraint.test(newv))
		txf.setText(oldv);
	});
    }
}
