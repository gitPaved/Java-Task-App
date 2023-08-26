package com.task.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.task.methods.Methods;


import com.task.bean.TaskBean;



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

		CustomLabel title = new CustomLabel(task.getTitle(),Font.BOLD,20);
		CustomLabel description = new CustomLabel(task.getDescription(),Font.HANGING_BASELINE,15);
		
		
		JPanel footer =new JPanel();
		footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
		
		JPanel footerLeft =new JPanel();
		footerLeft.setLayout(new BoxLayout(footerLeft, BoxLayout.X_AXIS));
		footerLeft.setBackground(Color.GREEN);
		CustomLabel dueDate = new CustomLabel("16 Aout 2023",Font.HANGING_BASELINE,15);
		CustomLabel reminder = new CustomLabel("Demain",Font.HANGING_BASELINE,15);
		footerLeft.add(dueDate);
		footerLeft.add(Box.createHorizontalGlue());
		footerLeft.add(reminder);
		
		
		JPanel footerRight =new JPanel();
		footerRight.setLayout(new BoxLayout(footerRight, BoxLayout.X_AXIS));
		footerRight.setBackground(Color.BLUE);		
		CustomLabel important = new CustomLabel("Important",Font.HANGING_BASELINE,15);
		CustomLabel finish = new CustomLabel("Terminee",Font.HANGING_BASELINE,15);
		footerRight.add(important);
		footerRight.add(Box.createHorizontalGlue());
		footerRight.add(finish);
		
		
		footer.setBackground(Color.CYAN);
		footer.add(footerLeft);
		footer.add(Box.createHorizontalGlue());
		footer.add(footerRight);

		add(title);
		add(Box.createVerticalStrut(10));
		add(description);
		add(Box.createVerticalStrut(10));
		add(footer);
	}

}
