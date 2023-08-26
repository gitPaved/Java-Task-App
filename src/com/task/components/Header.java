package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel textTitle;

	public Header(String titleHeader) {

		this.textTitle = new JLabel(titleHeader);
		textTitle.setFont(new Font("Courier New", Font.ITALIC + Font.BOLD, 30));
		textTitle.setHorizontalAlignment(JLabel.CENTER);

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		add(textTitle, BorderLayout.CENTER);

	}

}
