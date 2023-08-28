package com.task.test;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.task.bean.TaskBean;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class Test2 {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Date Selection Example");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();
			panel.setLayout(new FlowLayout());

			UtilDateModel model = new UtilDateModel();
			Properties properties = new Properties();
			properties.put("text.today", "Today");
			properties.put("text.month", "Month");
			properties.put("text.year", "Year");

			JDatePanelImpl datePanel = new JDatePanelImpl(model, properties);
			JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

			panel.add(datePicker);

			frame.getContentPane().add(panel);
			frame.pack();
			frame.setVisible(true);
		});
	}

	static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private final String datePattern = "yyyy-MM-dd";
		private final java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws java.text.ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws java.text.ParseException {
			if (value != null) {
				return dateFormatter.format(value);
			}
			return "";
		}
	}

//	List<TaskBean> taskList = new ArrayList<>();
//	for (int i = 1; i <= 20; i++) {
//		String title = "#" + i + " Create a website ";
//		String description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum "
//				+ i;
//		TaskBean task = new TaskBean(title, description);
//		task.setImportant(true);
//		if (i % 2 == 0) {
//			task.setImportant(true);
//			task.setFinish(true);
//		} else {
//			task.setImportant(false);
//			task.setFinish(false);
//		}
//
//		taskList.add(task);
//
//	}

//	List<TaskBean> tasksImportant = sqliteConnector.selectTasksImportant(connection, "modification_date");
//	List<TaskBean> tasksFinished = sqliteConnector.selectTasksFinished(connection, "modification_date");
//	List<TaskBean> tasksDueDate = sqliteConnector.selectTasksDueDate(connection, "modification_date");
//	List<TaskBean> tasks = sqliteConnector.selectTasksCreated(connection, "modification_date");
//
//	MenuOption option1 = new MenuOption("Importantes",
//			tasksImportant != null ? Integer.toString(tasksImportant.size()) : "");
//	MenuOption option2 = new MenuOption("Terminées",
//			tasksFinished != null ? Integer.toString(tasksFinished.size()) : "");
//	MenuOption option3 = new MenuOption("Planifiéess",
//			tasksDueDate != null ? Integer.toString(tasksDueDate.size()) : "");
//	MenuOption option4 = new MenuOption("Taches", tasks != null ? Integer.toString(tasks.size()) : "");
//
//	option1.addMouseListener(new EcouteurMenuOption(() -> {
//		if (tasksImportant != null) {
//			setContentPanelTasks("Importantes", tasksImportant);
//		} else {
//			JOptionPane.showMessageDialog(null, "Erreur lors de recuperation des donnees", "ERREUR !",
//					JOptionPane.ERROR_MESSAGE);
//		}
//		setSelectedOption(option1);
//	}));
//	option2.addMouseListener(new EcouteurMenuOption(() -> {
//		setContentPanelTasks("Terminées", tasksFinished);
//		setSelectedOption(option2);
//	}));
//	option3.addMouseListener(new EcouteurMenuOption(() -> {
//		setContentPanelTasks("Planifiéess", tasksDueDate);
//		setSelectedOption(option3);
//	}));
//	option4.addMouseListener(new EcouteurMenuOption(() -> {
//		setContentPanelTasks("Taches", tasks);
//		setSelectedOption(option4);
//	}));
//
//	panelContentMenu.add(option1);
//	panelContentMenu.add(Box.createRigidArea(new Dimension(0, 20)));
//	panelContentMenu.add(option2);
//	panelContentMenu.add(Box.createRigidArea(new Dimension(0, 22)));
//	panelContentMenu.add(option3);
//	panelContentMenu.add(Box.createRigidArea(new Dimension(0, 20)));
//	panelContentMenu.add(option4);
//	panelContentMenu.add(Box.createRigidArea(new Dimension(0, 10)));
	
	
//	for (int i = 1; i <= 20; i++) {
//		String title = "#" + i + " Create a website ";
//		String description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum "
//				+ i;
//		TaskBean task = new TaskBean(title, description);
//		defaultTask.add(task);
//	}
}
