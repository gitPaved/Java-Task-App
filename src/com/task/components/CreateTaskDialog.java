package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.task.bean.TaskBean;
import com.task.ecouteurs.EcouteurButton;
import com.task.interfaces.SelectTasks;
import com.task.methods.Methods;
import com.task.sql.SQLiteConnector;

public class CreateTaskDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();
	int id = 0;
	String title = "", description = "";
	Date dueDate = null, reminderDate = null;
	boolean important = false, finished = false;
	SQLiteConnector sqliteConnector = new SQLiteConnector();
	Connection connection = sqliteConnector.getConnection();
	SelectTasks selectTasks;

	public CreateTaskDialog(Frame owner, TaskBean task, SelectTasks selectTasks) {
		super(owner, "Créer une Nouvelle Tâche", true);
		this.selectTasks = selectTasks;

		setSize(500, 650);
		setBackground(Color.WHITE);

		if (task != null) {
			id = task.getId();
			title = task.getTitle();
			description = task.getDescription();
			important = task.getImportant();
			finished = task.getFinish();
			dueDate = task.getDueDate();
			reminderDate = task.getReminder();
		}

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

		// Title
		CustomLabel titleLabel = new CustomLabel("Titre", Font.PLAIN, 15);
		JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelTitle.add(titleLabel);
		panelTitle.setBackground(Color.WHITE);

		CustomTextAreaJScrollPane titleTextArea = new CustomTextAreaJScrollPane(title, Font.PLAIN, 15, true,
				Color.WHITE, null);
		titleTextArea.setPreferredSize(new Dimension(this.getPreferredSize().width, 75));

		JPanel panelTitleAndTextArea = new JPanel();
		panelTitleAndTextArea.setLayout(new BoxLayout(panelTitleAndTextArea, BoxLayout.Y_AXIS));

		panelTitleAndTextArea.add(panelTitle);
		panelTitleAndTextArea.add(titleTextArea);
		panelTitleAndTextArea.setBackground(Color.WHITE);

		// Description
		CustomLabel descriptionLabel = new CustomLabel("Description", Font.PLAIN, 15);
		JPanel panelDescription = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelDescription.add(descriptionLabel);
		panelDescription.setBackground(Color.WHITE);

		CustomTextAreaJScrollPane descriptionTextArea = new CustomTextAreaJScrollPane(description, Font.PLAIN, 15, true,
				Color.WHITE, null);
		descriptionTextArea.setPreferredSize(new Dimension(this.getPreferredSize().width, 180));

		JPanel panelDescriptionAndTextArea = new JPanel();
		panelDescriptionAndTextArea.setLayout(new BoxLayout(panelDescriptionAndTextArea, BoxLayout.Y_AXIS));

		panelDescriptionAndTextArea.add(panelDescription);
		panelDescriptionAndTextArea.add(descriptionTextArea);
		panelDescriptionAndTextArea.setBackground(Color.WHITE);

		// Due date
		CustomLabel dueDateLabel = new CustomLabel("Date echeance", Font.PLAIN, 15);
		CustomDatePicker dueDatePanel = new CustomDatePicker(dueDate);

		JPanel panelDueDate = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelDueDate.add(dueDateLabel);
		panelDueDate.add(dueDatePanel);
		panelDueDate.setBackground(Color.WHITE);

		// Reminder date
		CustomLabel reminderDateLabel = new CustomLabel("Date de Rappel", Font.PLAIN, 15);
		CustomDatePicker reminderDatePanel = new CustomDatePicker(reminderDate);

		JPanel panelReminderDate = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelReminderDate.add(reminderDateLabel);
		panelReminderDate.add(reminderDatePanel);
		panelReminderDate.setBackground(Color.WHITE);

		// Important
		CustomLabel importantLabel = new CustomLabel("Importante", Font.PLAIN, 15);
		JCheckBox importantCheckBox = new JCheckBox("");
		importantCheckBox.setSelected(important);
		importantCheckBox.setBackground(Color.WHITE);
		setFont(new Font("Comic Sans MS", Font.BOLD, 15));

		JPanel panelImportant = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelImportant.add(importantLabel);
		panelImportant.add(importantCheckBox);
		panelImportant.setBackground(Color.WHITE);

		// Finished
		CustomLabel finishedLabel = new CustomLabel("Terminée", Font.PLAIN, 15);
		JCheckBox finishCheckBox = new JCheckBox("");
		finishCheckBox.setSelected(finished);
		finishCheckBox.setBackground(Color.WHITE);

		JPanel panelFinished = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelFinished.add(finishedLabel);
		panelFinished.add(finishCheckBox);
		panelFinished.setBackground(Color.WHITE);

		JPanel panelImportantAndFinish = new JPanel();
		panelImportantAndFinish.setLayout(new BoxLayout(panelImportantAndFinish, BoxLayout.X_AXIS));
		panelImportantAndFinish.add(panelImportant, BorderLayout.WEST);
		panelImportantAndFinish.add(Box.createHorizontalGlue());
		panelImportantAndFinish.add(panelFinished, BorderLayout.EAST);
		panelImportantAndFinish.setBackground(Color.WHITE);

		// Button
		CustomButton createButton = new CustomButton("Créer", 15);
		createButton.addMouseListener(new EcouteurButton());
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (titleTextArea.getText().trim().equals("")) {
					errorField("Entrer un titre");
					return;
				}
				System.out.println("titleTextArea is empty ------------ ");
				onPressCreateUpdate(true, titleTextArea.getText().trim(), descriptionTextArea.getText().trim(),
						dueDatePanel.getDate(), reminderDatePanel.getDate(), importantCheckBox.isSelected(),
						finishCheckBox.isSelected());

			}
		});

		CustomButton updateButton = new CustomButton("Modifier", 15);
		updateButton.addMouseListener(new EcouteurButton());
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (titleTextArea.getText().trim().equals("")) {
					errorField("Entrer un titre");
					return;
				}
				System.out.println("titleTextArea is empty ------------  dueDatePanel "
						+ dueDatePanel.dateLabelFormatter.isChanged);
				Date dueDate = dueDatePanel.dateLabelFormatter.isChanged ? dueDatePanel.getDate() : task.getDueDate();
				Date reminderDate = reminderDatePanel.dateLabelFormatter.isChanged ? reminderDatePanel.getDate()
						: task.getReminder();

				onPressCreateUpdate(false, titleTextArea.getText().trim(), descriptionTextArea.getText().trim(),
						dueDate, reminderDate, importantCheckBox.isSelected(), finishCheckBox.isSelected());

			}
		});

		CustomButton cancelButton = new CustomButton("Annuler", 15);
		cancelButton.addMouseListener(new EcouteurButton());
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel panelButtonDialog = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
		panelButtonDialog.add(cancelButton);
		panelButtonDialog.add(createButton);
		panelButtonDialog.add(updateButton);
		panelButtonDialog.setBackground(Color.WHITE);
		if (task != null) {
			updateButton.setVisible(true);
			createButton.setVisible(false);
		} else {
			updateButton.setVisible(false);
			createButton.setVisible(true);
		}

		// Principal

		panelPrincipal.add(panelTitleAndTextArea);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(panelDescriptionAndTextArea);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(panelDueDate);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(panelReminderDate);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(panelImportantAndFinish);
		panelPrincipal.add(Box.createVerticalStrut(10));
		panelPrincipal.add(panelButtonDialog);
		panelPrincipal.setBackground(Color.WHITE);
		methods.addPaddingPanel(panelPrincipal, 10, 10, 10, 10);

		add(new JScrollPane(panelPrincipal));
		setLocationRelativeTo(owner);
	}

	public void onPressCreateUpdate(boolean create, String title, String description, Date dueDate, Date reminderDate,
			boolean important, boolean finish) {

		String res = "";
		TaskBean task = new TaskBean(id, title, description, dueDate, reminderDate, important, finish);
		if (create) {
			res = sqliteConnector.insertTask(connection, task);
		} else {
			res = sqliteConnector.updateTask(connection, task);
		}
		if (res.equals("true")) {
			selectTasks.execute();
			dispose();
		} else {
			JOptionPane.showMessageDialog(null, res, "ERREUR !", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void errorField(String text) {
		JOptionPane.showMessageDialog(null, text, "ERREUR !", JOptionPane.ERROR_MESSAGE);
	}
}
