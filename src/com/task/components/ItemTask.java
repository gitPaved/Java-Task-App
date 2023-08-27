package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.task.bean.TaskBean;
import com.task.methods.Methods;

public class ItemTask extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();
	TaskBean task;

	public ItemTask(TaskBean task) {
		this.task = task;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);

		methods.addRounderAndPaddingPanel(this, 10, Color.lightGray, 10, 10, 10, 10);

		CustomJTextArea title = new CustomJTextArea(task.getTitle(), Font.BOLD, 22, false, null, null);
		JPanel panelTitle = new JPanel(new BorderLayout());
		panelTitle.add(title);
		panelTitle.setBackground(Color.WHITE);
		methods.addPaddingPanel(panelTitle, 0, 10, 0, 10);

		CustomJTextArea description = new CustomJTextArea(task.getDescription(), Font.PLAIN, 15, false, null, null);
		JPanel panelDescription = new JPanel(new BorderLayout());
		panelDescription.add(description, BorderLayout.CENTER);
		panelDescription.setBackground(Color.WHITE);
		methods.addPaddingPanel(panelDescription, 0, 10, 0, 10);

		JPanel footerLeft = new JPanel(new FlowLayout(0, 10, 5));
		footerLeft.setBackground(Color.WHITE);
		CustomLabel dueDate = new CustomLabel("Planifiee: 16 Aout 2023", Font.BOLD, 12);
		CustomLabel reminder = new CustomLabel("Rappel: Demain", Font.BOLD, 12);
		footerLeft.add(dueDate);
		footerLeft.add(reminder);

		JPanel footerRight = new JPanel(new FlowLayout(0, 10, 5));
		footerRight.setBackground(Color.WHITE);
		CustomLabel important = new CustomLabel("Important", Font.BOLD, 12);
		CustomLabel finish = new CustomLabel("Terminee", Font.BOLD, 12);
		footerRight.add(important);
		footerRight.add(finish);

		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(Color.WHITE);
		footer.add(footerLeft, BorderLayout.WEST);
		footer.add(footerRight, BorderLayout.EAST);

		add(panelTitle);
		add(panelDescription);
		add(footer);
	}

}
