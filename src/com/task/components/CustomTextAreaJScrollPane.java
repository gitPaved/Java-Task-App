package com.task.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class CustomTextAreaJScrollPane extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;

	public CustomTextAreaJScrollPane(String text, int font, int size, boolean editable, Color color, Border border) {
		textArea = new JTextArea(text);
		textArea.setFont(new Font("Comic Sans MS", font, size));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(editable);
		textArea.setBackground(color);
		textArea.setBorder(border);

		setViewportView(textArea);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	public String getText() {
		return textArea.getText();
	}
}
