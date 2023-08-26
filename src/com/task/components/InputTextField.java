package com.task.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.task.interfaces.SearchTasks;



/**
 * 
 * */


public class InputTextField extends JTextField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String placeholder;
	SearchTasks searchTasks;

	public InputTextField(SearchTasks searchTasks, String placeholder) {
		this.placeholder = placeholder;
		this.searchTasks = searchTasks;

		setForeground(Color.GRAY);
		Font customFont = new Font("Courier New", Font.BOLD, 16);
		setFont(customFont);

		updatePlaceholderDisplay();
		addFocusListener(new PlaceholderFocusListener());
		getDocument().addDocumentListener(new PlaceholderDocumentListener());
		
		setBackground(Color.WHITE);
	}

	private class PlaceholderFocusListener implements FocusListener {
		@Override
		public void focusGained(FocusEvent e) {
			if (getText().equals(placeholder)) {
				setText("");
				setForeground(Color.BLACK);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			updatePlaceholderDisplay();
		}
	}

	private class PlaceholderDocumentListener implements DocumentListener {
		@Override
		public void insertUpdate(DocumentEvent e) {
			updateCharacterLabel();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			updateCharacterLabel();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}

		private void updateCharacterLabel() {
			String enteredText = getText();
			if (searchTasks != null) {
				searchTasks.execute(enteredText);
			}
		}
	}

	private void updatePlaceholderDisplay() {
		if (getText().isEmpty()) {
			setText(placeholder);
			setForeground(Color.GRAY);
		}
	}
}
