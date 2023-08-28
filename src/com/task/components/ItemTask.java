package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.task.bean.TaskBean;
import com.task.interfaces.OnPressTask;
import com.task.methods.Methods;

public class ItemTask extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();
	TaskBean task;
	String datePattern = "dd MMM yyyy";
	String dateTimePattern = "dd MMM yyyy HH:mm:ss";
	OnPressTask onPressTask;

	public ItemTask(TaskBean task, OnPressTask onPressTask) {
		this.task = task;
		this.onPressTask = onPressTask;

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

		if (task.getDueDate() != null) {
			CustomLabel dueDate = new CustomLabel(
					"Planifiée: " + methods.convertDateToString(task.getDueDate(), datePattern), Font.BOLD, 12);
			dueDate.setForeground(Color.blue);
			footerLeft.add(dueDate);
		}
		if (task.getReminder() != null) {
			CustomLabel reminder = new CustomLabel(
					"Rappel: " + methods.convertDateToString(task.getReminder(), datePattern), Font.BOLD, 12);
			reminder.setForeground(Color.GREEN);
			footerLeft.add(reminder);
		}

		JPanel footerRight = new JPanel(new FlowLayout(0, 10, 5));
		footerRight.setBackground(Color.WHITE);
		CustomLabel important = new CustomLabel("Importante", Font.BOLD, 12);
		important.setForeground(Color.blue);
		CustomLabel finish = new CustomLabel("Terminée", Font.BOLD, 12);
		if (task.getImportant()) {
			footerRight.add(important);
		}
		if (task.getFinish()) {
			footerRight.add(finish);
		}

		

		JPanel createUpdatePanel = new JPanel(new BorderLayout());
		createUpdatePanel.setBackground(Color.WHITE);
		methods.addPaddingPanel(createUpdatePanel, 0, 10, 0, 10);
		
		if (task.getCreateDate() != null) {
			CustomLabel createDate = new CustomLabel(
					"Créée: " + methods.convertDateToString(task.getCreateDate(), dateTimePattern), Font.BOLD, 12);
//			createDate.setForeground(Color.blue);
			createUpdatePanel.add(createDate, BorderLayout.WEST);
		}
		if (task.getUpdateDate() != null) {
			CustomLabel updateDate = new CustomLabel(
					"Modifiée: " + methods.convertDateToString(task.getUpdateDate(), dateTimePattern), Font.BOLD, 12);
//			createDate.setForeground(Color.blue);
			createUpdatePanel.add(updateDate, BorderLayout.EAST);
		}

		JPanel panelAction = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
		CustomButton btnEditerTask = new CustomButton("Editer", 14);
		CustomButton btnRemove = new CustomButton("Supprimer", 14);

		btnEditerTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				onPressTask.execute(task,"edit");
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				onPressTask.execute(task,"delete");
			}
		});

		panelAction.add(btnEditerTask);
		panelAction.add(btnRemove);
		panelAction.setBackground(Color.WHITE);
		
		
		JPanel footer = new JPanel(new BorderLayout());
		footer.setBackground(Color.WHITE);
		footer.add(footerLeft, BorderLayout.WEST);
		footer.add(footerRight, BorderLayout.EAST);

		add(panelTitle);
		add(panelDescription);
		add(footer);
		add(createUpdatePanel);
		add(panelAction);

	}

	public TaskBean getTaskBean() {
		return this.task;
	}

}
