package com.task.components;

import java.awt.Color;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.task.methods.Methods;

public class CustomDatePicker extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();
	JDatePickerImpl datePicker;
	String pattern = "yyyy-MM-dd";
	DateLabelFormatter dateLabelFormatter; 
	
	
	public CustomDatePicker(Date date) { 

		UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");

		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		dateLabelFormatter = new DateLabelFormatter(date);
		datePicker = new JDatePickerImpl(datePanel, dateLabelFormatter);//
		datePicker.setBackground(Color.white);
		add(datePicker);
		setBackground(Color.white);
	}

	public Date getDate() {
		UtilDateModel dateModel = (UtilDateModel) datePicker.getModel();
		Date selectedDate = dateModel.getValue();
		return selectedDate;  
	}
}
