package com.task.components;

import java.awt.Font;

import javax.swing.JButton;

import com.task.methods.Methods;

public class CustomButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Methods methods = new Methods();

	public CustomButton(String text, int size) {
		setText(text);
		setFont(new Font("Comic Sans MS", Font.BOLD, size));
		methods.addPaddingBtn(this, 5, 10, 5, 10);
	}

}
