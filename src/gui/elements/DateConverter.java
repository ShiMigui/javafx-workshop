package gui.elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.control.DatePicker;
import javafx.util.StringConverter;

public class DateConverter extends StringConverter<LocalDate> {
    private static DateTimeFormatter fmt;
    private DatePicker picker;

    public DateConverter(DatePicker picker, String format) {
	this.picker = picker;
	setFormat(format);
    }

    public void setFormat(String format) {
	fmt = DateTimeFormatter.ofPattern(format);
	picker.setPromptText(format);
    }

    @Override
    public LocalDate fromString(String string) {
	return (string != null && !string.isEmpty()) ? LocalDate.parse(string, fmt) : null;
    }

    @Override
    public String toString(LocalDate date) {
	return (date != null) ? fmt.format(date) : "";
    }

}
