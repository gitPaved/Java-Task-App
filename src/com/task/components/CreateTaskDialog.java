package com.task.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.task.methods.Methods;

public class CreateTaskDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Methods methods = new Methods();

	public CreateTaskDialog(Frame owner) {
		super(owner, "Créer une Nouvelle Tâche", true);
		setSize(500, 650);
		setBackground(Color.WHITE);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		
		
		UtilDateModel model = new UtilDateModel();
		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		
		

		// Title
		CustomLabel title = new CustomLabel("Titre", Font.PLAIN, 15);
		JPanel panelTitle = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelTitle.add(title);
		panelTitle.setBackground(Color.WHITE);

		CustomJTextArea titleTextArea = new CustomJTextArea("", Font.PLAIN, 15, true, Color.WHITE,
				BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		JPanel panelTitleAndTextArea = new JPanel();
		panelTitleAndTextArea.setLayout(new BoxLayout(panelTitleAndTextArea, BoxLayout.Y_AXIS));

		panelTitleAndTextArea.add(panelTitle);
		panelTitleAndTextArea.add(titleTextArea);
		panelTitleAndTextArea.setBackground(Color.WHITE);

		// Description
		CustomLabel description = new CustomLabel("Description", Font.PLAIN, 15);
		JPanel panelDescription = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelDescription.add(description);
		panelDescription.setBackground(Color.WHITE);

		CustomJTextArea descriptionTextArea = new CustomJTextArea("", Font.PLAIN, 15, true, Color.WHITE,
				BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		JPanel panelDescriptionAndTextArea = new JPanel();
		panelDescriptionAndTextArea.setLayout(new BoxLayout(panelDescriptionAndTextArea, BoxLayout.Y_AXIS));

		panelDescriptionAndTextArea.add(panelDescription);
		panelDescriptionAndTextArea.add(descriptionTextArea);
		panelDescriptionAndTextArea.setBackground(Color.WHITE);

		// Due date
		CustomLabel dueDate = new CustomLabel("Date echeance", Font.PLAIN, 15);		
		

		JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());		
		
		
		JPanel panelDueDate = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelDueDate.add(dueDate);
		panelDueDate.add(datePicker);
		panelDueDate.setBackground(Color.WHITE);
		
		
		
		
		// Reminder date
		CustomLabel reminderDate = new CustomLabel("Date de Rappel", Font.PLAIN, 15);

		JDatePanelImpl reminderDatePanel = new JDatePanelImpl(model, properties);
		JDatePickerImpl remiderDatePicker = new JDatePickerImpl(reminderDatePanel, new DateLabelFormatter());	
		
		
		JPanel panelReminderDate = new JPanel(new FlowLayout(FlowLayout.LEFT, 2, 0));
		panelReminderDate.add(reminderDate);
		panelReminderDate.add(remiderDatePicker);
		panelReminderDate.setBackground(Color.WHITE);
				

		// Important
		CustomLabel important = new CustomLabel("Importante", Font.PLAIN, 15);
		JPanel panelImportant = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelImportant.add(important);
		panelImportant.setBackground(Color.WHITE);

		ButtonGroup groupButtonImportant = new ButtonGroup();

		JRadioButton yes = new JRadioButton("Oui");
		yes.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		yes.setBackground(Color.WHITE);

		JRadioButton no = new JRadioButton("Non");
		no.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		no.setBackground(Color.WHITE);

//		b1.addActionListener(new Ecout());
//		b2.addActionListener(new Ecout());
		groupButtonImportant.add(yes);
		groupButtonImportant.add(no);

		JPanel panelImportantButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panelImportantButton.add(yes);
		panelImportantButton.add(no);
		panelImportantButton.setBackground(Color.WHITE);

		JPanel panelImportantAndButton = new JPanel();
		panelImportantAndButton.setLayout(new BoxLayout(panelImportantAndButton, BoxLayout.Y_AXIS));

		panelImportantAndButton.add(panelImportant);
		panelImportantAndButton.add(panelImportantButton);
		panelImportantAndButton.setBackground(Color.WHITE);
		
		

		// Finished
		CustomLabel finished = new CustomLabel("Terminee", Font.PLAIN, 15);
		JPanel panelFinished = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelFinished.add(finished);
		panelFinished.setBackground(Color.WHITE);

		ButtonGroup groupButtonFinished = new ButtonGroup();

		JRadioButton yesFinished = new JRadioButton("Oui");
		yesFinished.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		yesFinished.setBackground(Color.WHITE);

		JRadioButton noFinished = new JRadioButton("Non");
		noFinished.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		noFinished.setBackground(Color.WHITE);

//		b1.addActionListener(new Ecout());
//		b2.addActionListener(new Ecout());
		groupButtonFinished.add(yesFinished);
		groupButtonFinished.add(noFinished);

		JPanel panelFinishedButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panelFinishedButton.add(noFinished);
		panelFinishedButton.add(yesFinished);
		panelFinishedButton.setBackground(Color.WHITE);

		JPanel panelFinishedAndButton = new JPanel();
		panelFinishedAndButton.setLayout(new BoxLayout(panelFinishedAndButton, BoxLayout.Y_AXIS));

		panelFinishedAndButton.add(panelFinished);
		panelFinishedAndButton.add(panelFinishedButton);
		panelFinishedAndButton.setBackground(Color.WHITE);

		JButton createButton = new JButton("Créer");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				String taskName = nameField.getText();
				dispose();
			}
		});

		panelPrincipal.add(panelTitleAndTextArea);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelDescriptionAndTextArea);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelImportantAndButton);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelDueDate);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelReminderDate);
		panelPrincipal.add(Box.createVerticalStrut(15));
		panelPrincipal.add(panelFinishedAndButton);
		panelPrincipal.add(createButton);
		panelPrincipal.setBackground(Color.WHITE);
		methods.addPaddingPanel(panelPrincipal, 10, 10, 10, 10);

		add(new JScrollPane(panelPrincipal));
		setLocationRelativeTo(owner);
	}
}
