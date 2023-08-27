package com.task.components;

import java.awt.Font;

import javax.swing.JLabel;

import com.task.methods.Methods;

public class CustomLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Methods methods = new Methods();

	public CustomLabel(String text, int font, int size) {
		setText(text);
		setFont(new Font("Comic Sans MS", font, size)); //Helvetica Courier New Calibri Times New Roman Georgia Comic Sans MS
	}

}
