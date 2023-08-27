package com.task.components;

import java.util.Calendar;

import javax.swing.JFormattedTextField;

public class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String datePattern = "dd-MM-yyyy";
	private final java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

	@Override
	public Object stringToValue(String text) throws java.text.ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws java.text.ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}
		return "";
	}
}
