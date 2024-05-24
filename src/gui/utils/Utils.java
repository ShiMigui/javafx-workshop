package gui.utils;

import gui.elements.DateConverter;
import javafx.scene.control.DatePicker;

public class Utils {
    private Utils() {
    }

    public static void formatDatePicker(DatePicker datePicker, String format) {
	datePicker.setConverter(new DateConverter(datePicker, format));
    }
}
