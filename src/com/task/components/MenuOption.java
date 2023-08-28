package com.task.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.task.methods.Methods;

public class MenuOption extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();
	String label;
	String value; 

	public MenuOption(String label, String value) {
		this.label = label;
		this.value = value;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.WHITE);
		methods.addRounderAndPaddingPanel(this, 10, Color.lightGray, 10, 10, 10, 10);

		CustomLabel labelOption = new CustomLabel(label, Font.BOLD,14);
		CustomLabel valueOption = new CustomLabel(value,Font.BOLD, 18);

		add(labelOption);
		add(Box.createHorizontalGlue());
		add(valueOption);
	}

}
