package com.task.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.task.interfaces.SearchTasks;

public class Search extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel characterLabel;
	InputTextField inputText;

	public Search(SearchTasks searchTaks) {
		inputText = new InputTextField(searchTaks, "Placeholder Text");
		inputText.setColumns(20);

		ImageIcon searchImage = new ImageIcon(this.getClass().getResource("images/loupe.png"));
		Image resizedImage = searchImage.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		ImageIcon resizedImageIcon = new ImageIcon(resizedImage);
		JLabel imageLabel = new JLabel(searchImage);
		characterLabel = new JLabel("Caractères saisis : ");

		setLayout(new FlowLayout());
		setBackground(Color.WHITE);
		add(inputText);
		add(imageLabel);
		SwingUtilities.invokeLater(() -> {
			requestFocusInWindow();
		});
	}

}
