package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.task.bean.OptionBean;
import com.task.bean.SortOptionBean;
import com.task.bean.TaskBean;
import com.task.ecouteurs.EcouteurMenuOption;
import com.task.methods.Methods;
import com.task.sql.SQLiteConnector;

public class Task extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelPrincipal, panelMenu, panelTasks, panelSearch, panelContentMenu, panelContentTaks;
	InputTextField search;
	CustomLabel titleTask;
	JScrollPane panelTasksScroll;
	Methods methods = new Methods();
	String orderField = "Taches", sortField = "title", searchTextTask = "";
	MenuOption selectedOption;
	SQLiteConnector sqliteConnector = new SQLiteConnector();
	Connection connection = sqliteConnector.getConnection();
	JComboBox<SortOptionBean> comboBox;
	SortOptionBean targetSortOption;
	CustomButton btnAddTask, btnReset;
	JScrollBar verticalScrollBarPanelTasks;

	public Task() {

		// JPanel
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelTasks = new JPanel();
		panelContentMenu = new JPanel();
		panelContentTaks = new JPanel();
		panelTasksScroll = new JScrollPane(panelTasks);
		verticalScrollBarPanelTasks = panelTasksScroll.getVerticalScrollBar();

		//
		titleTask = new CustomLabel(orderField, Font.BOLD, 24);
		btnAddTask = new CustomButton("Ajouter une tache", 14);
		btnReset = new CustomButton("Réinitialiser", 14);

		setSize(1000, 600);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Task");
		setContentPane(panelPrincipal);
		addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				quit();
			}

		});

		init();
		initMenu();
		initListTask();
	}

	public void init() {
		JSplitPane splitPanelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panelMenu),
				panelTasksScroll);
		splitPanelPrincipal.setDividerLocation(350);

		//Header header = new Header("GESTIONNAIRE DE TACHES");
		panelPrincipal.setLayout(new BorderLayout());
		//panelPrincipal.add(header, BorderLayout.NORTH);
		panelPrincipal.add(splitPanelPrincipal, BorderLayout.CENTER);
	}

	public void initMenu() {
		search = new InputTextField(enteredText -> {
			System.out.println("Entered Text: " + enteredText);
			searchTextTask = search.isFocus && enteredText == "" ? "" : enteredText;
			SortOptionBean selectedOption = (SortOptionBean) comboBox.getSelectedItem();
			if (selectedOption != null) {
				sortField = selectedOption.getField();
			}
			searchedTasks(orderField, sortField, searchTextTask);

		}, "");
		
		
		CustomLabel labelMenu = new CustomLabel("Gestionnaire", Font.BOLD,20);
		CustomLabel labelSearch = new CustomLabel("Rechercher", Font.BOLD,15);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
		
		
		panelHeader.add(labelMenu);
		panelHeader.add(Box.createVerticalStrut(10));
		panelHeader.add(labelSearch);
		panelHeader.add(search);
		panelHeader.setBackground(Color.WHITE);
		
		
		panelContentMenu.setLayout(new BoxLayout(panelContentMenu, BoxLayout.Y_AXIS));
		panelContentMenu.setBackground(Color.WHITE);
		methods.addPaddingPanel(panelContentMenu, 25, 0, 10, 0);

		setContentPanelMenu();

		btnAddTask.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddTask.requestFocusInWindow();
				openCreateTaskDialog(null);
			}
		});

		btnReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnReset.requestFocusInWindow();
				reset();
			}
		});

		JPanel panelBtnAddTask = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		panelBtnAddTask.setBackground(Color.WHITE);
		panelBtnAddTask.add(btnAddTask);
		panelBtnAddTask.add(btnReset);

		panelMenu.setLayout(new BorderLayout());
		methods.addPaddingPanel(panelMenu, 20, 20, 20, 20);
		panelMenu.add(panelHeader, BorderLayout.NORTH);
		panelMenu.add(panelContentMenu, BorderLayout.CENTER);
		panelMenu.add(panelBtnAddTask, BorderLayout.SOUTH);
		panelMenu.setBackground(Color.WHITE);

	}

	public void setContentPanelMenu() {

		panelContentMenu.removeAll();

		List<OptionBean> optionsBean = getOptionsBean();

		for (OptionBean option : optionsBean) {
			List<TaskBean> tasks = option.getTasks();
			MenuOption menuOption = new MenuOption(option.getLabel(),
					tasks != null ? Integer.toString(tasks.size()) : "");
			menuOption.addMouseListener(new EcouteurMenuOption(() -> {
				setSelectedOption(menuOption);
				orderField = option.getLabel();
				searchedTasks(orderField, sortField, searchTextTask);
			}));
			if (option.isDefaultOption()) {				
					selectedOption = menuOption;
					setSelectedOption(selectedOption);				
			}
			panelContentMenu.add(menuOption);
			panelContentMenu.add(Box.createRigidArea(new Dimension(0, 20)));
		}

		panelContentMenu.revalidate();
		panelContentMenu.repaint();

	}

	public void setSelectedOption(MenuOption option) {
		if (selectedOption != null) {
			selectedOption.setBackground(Color.WHITE);
		}
		selectedOption = option;
		if (selectedOption != null) {
			selectedOption.setBackground(new Color(200, 200, 200));
		}
	}

	public void initListTask() {
		panelTasks.setBackground(Color.WHITE);
		panelTasks.setLayout(new BorderLayout());
		methods.addPaddingPanel(panelTasks, 20, 20, 20, 20);
		selectedTasks("modification_date");

		JPanel panelHeaderTask = new JPanel(new BorderLayout());
		panelHeaderTask.setBackground(Color.WHITE);
		panelHeaderTask.add(titleTask, BorderLayout.WEST);

		List<SortOptionBean> sortOptions = getSortOptions();
		int index = sortOptions.indexOf(targetSortOption);

		comboBox = new JComboBox<>(sortOptions.toArray(new SortOptionBean[index]));
		comboBox.setBackground(Color.white);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnAddTask.requestFocusInWindow();
				SortOptionBean selectedSortOption = (SortOptionBean) comboBox.getSelectedItem();
				targetSortOption = selectedSortOption;
				sortField = selectedSortOption.getField();
				// System.err.println("orderField ---- " + orderField);
				searchedTasks(orderField, sortField, searchTextTask);
			}
		});

		JPanel panelSort = new JPanel(new FlowLayout(0, 15, 0));
		JLabel sortLable = new JLabel("Trie par");
		panelSort.add(sortLable);
		panelSort.add(comboBox);
		panelSort.setBackground(Color.white);

		panelHeaderTask.add(panelSort, BorderLayout.EAST);
		methods.addPaddingPanel(panelHeaderTask, 0, 0, 25, 0);

		panelTasks.add(panelHeaderTask, BorderLayout.NORTH);
		panelTasks.add(panelContentTaks, BorderLayout.CENTER);

		verticalScrollBarPanelTasks.setValue(0);
		verticalScrollBarPanelTasks.revalidate();

	}

	public void setContentPanelTasks(String title, List<TaskBean> tasks) {

		titleTask.setText(title);

		panelContentTaks.removeAll();

		if (tasks.size() > 0) {
			panelContentTaks.setLayout(new BoxLayout(panelContentTaks, BoxLayout.Y_AXIS));
			ItemTask[] itmemTasks = new ItemTask[tasks.size()];
			for (int i = 0; i < itmemTasks.length; i++) {
				itmemTasks[i] = new ItemTask(tasks.get(i), ((task, action) -> {
					if (action == "edit") {
						openCreateTaskDialog(task);
					} else {
						deleteTask(task);
					}

				}));
			}

			int i = 0;
			for (ItemTask item : itmemTasks) {
				panelContentTaks.add(item);
				if (i % 2 == 0) {
					panelContentTaks.add(Box.createVerticalStrut(20));
				} else {
					panelContentTaks.add(Box.createVerticalStrut(25));
				}
				i++;

			}
		} else {
			panelContentTaks.setLayout(new BorderLayout());
			CustomLabel emptyLabel = new CustomLabel("Pas de taches trouvées", Font.BOLD, 20);
			emptyLabel.setHorizontalAlignment(CustomLabel.CENTER);
			panelContentTaks.add(emptyLabel, BorderLayout.CENTER);
		}
		panelContentTaks.setBackground(Color.white);
		panelContentTaks.revalidate();
		panelContentTaks.repaint();
				
		verticalScrollBarPanelTasks.setValue(0);
		verticalScrollBarPanelTasks.revalidate();

	}

	private void openCreateTaskDialog(TaskBean task) {
		CreateTaskDialog dialog = new CreateTaskDialog(this, task, (() -> {
			if (task == null) {
				selectedTasks("creation_date");
			} else {
				selectedTasks("modification_date");
			}

		}));
		dialog.setVisible(true);
	}

	public List<OptionBean> getOptionsBean() {
		List<OptionBean> list = new ArrayList<>();
		List<TaskBean> tasksImportant = sqliteConnector.selectTasksImportant(connection, "modification_date");
		List<TaskBean> tasksFinished = sqliteConnector.selectTasksFinished(connection, "modification_date");
		List<TaskBean> tasksDueDate = sqliteConnector.selectTasksDueDate(connection, "modification_date");
		List<TaskBean> tasks = sqliteConnector.selectTasksCreated(connection, "modification_date");

		OptionBean important = new OptionBean("Importantes", tasksImportant);
		OptionBean finish = new OptionBean("Terminées", tasksFinished);
		OptionBean dueDate = new OptionBean("Planifiées", tasksDueDate);
		OptionBean all = new OptionBean("Taches", tasks);
		all.setDefaultOption(true);

		list.add(important);
		list.add(finish);
		list.add(dueDate);
		list.add(all);

		return list;

	}

	public List<SortOptionBean> getSortOptions() {
		List<SortOptionBean> list = new ArrayList<>();
		SortOptionBean title = new SortOptionBean("Titre", "title");
		SortOptionBean creationDate = new SortOptionBean("Date de creation", "creation_date");
		SortOptionBean modificationDate = new SortOptionBean("Date de modification", "modification_date");

		list.add(title);
		list.add(creationDate);
		list.add(modificationDate);
		targetSortOption = title;
		return list;

	}

	public void selectedTasks(String orderFilter) {
		//List<TaskBean> tasks = sqliteConnector.selectTasksCreated(connection, orderFilter);
		List<TaskBean> tasks = sqliteConnector.searchTasks(connection, orderField, sortField, searchTextTask);
		if (tasks != null) {
			setContentPanelTasks(orderField, tasks);
			setContentPanelMenu();
		} else {
			JOptionPane.showMessageDialog(null, "Erreur lors de recuperation des donnees", "ERREUR !",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void searchedTasks(String order, String sort, String textSearch) {
		List<TaskBean> tasks = sqliteConnector.searchTasks(connection, order, sort, textSearch);
		if (tasks != null) {
			setContentPanelTasks(order, tasks);
		} else {
			JOptionPane.showMessageDialog(null, "Erreur lors de recuperation des donnees", "ERREUR !",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void quit() {

		int n = JOptionPane.showConfirmDialog(this, "Voulez-vous fermer l'application ?", "Fermer",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	public void reset() {

		int n = JOptionPane.showConfirmDialog(this, "Voulez-vous réinitialiser vos taches ?", "Réinitialiser",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			String rs = sqliteConnector.resetTableTasks(connection);
			if (rs.equals("true")) {
				connection = sqliteConnector.getConnection();
				selectedTasks("creation_date");
			} else {
				JOptionPane.showMessageDialog(null, rs, "ERREUR !", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public void deleteTask(TaskBean task) {

		int n = JOptionPane.showConfirmDialog(this, "Voulez-vous supprimez la tache ? \nTitre:" + task.getTitle(),
				"Supprimer", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			String rs = sqliteConnector.deleteTask(connection, task.getId());
			if (rs.equals("true")) {
				searchedTasks(orderField, sortField, searchTextTask);
				setContentPanelMenu();
			} else {
				JOptionPane.showMessageDialog(null, rs, "ERREUR !", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Task().setVisible(true);
			}
		});
	}

}
