package com.task.components;

import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.task.interfaces.SearchTasks;
import com.task.test.ImagePanel;

public class Search extends JPanel {

	private static final long serialVersionUID = 1L;
	JLabel characterLabel;
	InputTextField inputText;
	ImagePanel imageSearch;

	public Search(SearchTasks searchTaks) { 
		setLayout(new BorderLayout());		

		inputText = new InputTextField(searchTaks, "Rechercher ....");
		inputText.setMargin(new Insets(5, 5, 5, 5));

//		imageSearch = new ImagePanel("images/search_icon.png");

		add(inputText, BorderLayout.CENTER);
		SwingUtilities.invokeLater(() -> {
			requestFocusInWindow();
		});
	}
	
	public InputTextField getInputTextField() {
		return this.inputText; 
	}

}
