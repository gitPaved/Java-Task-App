package com.task.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.task.methods.Methods;

public class CustomJTextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Methods methods = new Methods();

	public CustomJTextArea(String text, int font, int size, boolean editable,Color color, Border border) {
		setText(text);
		setFont(new Font("Comic Sans MS", font, size)); // Helvetica Courier New Calibri Times New Roman Georgia Comic
														// Sans MS
		setLineWrap(true);
		setWrapStyleWord(true);
		setEditable(editable);
		setBackground(color);
		setBorder(border);
	}

}
