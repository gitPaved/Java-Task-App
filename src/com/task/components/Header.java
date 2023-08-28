package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Header extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	CustomLabel textTitle;

	public Header(String titleHeader) { 

		this.textTitle = new CustomLabel(titleHeader, Font.ITALIC + Font.BOLD, 30);
		textTitle.setHorizontalAlignment(CustomLabel.CENTER);

		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
		add(textTitle, BorderLayout.CENTER);

	}

}
