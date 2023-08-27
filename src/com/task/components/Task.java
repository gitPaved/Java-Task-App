package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.task.bean.TaskBean;
import com.task.ecouteurs.EcouteurMenuOption;
import com.task.ecouteurs.EcouteurTask;
import com.task.methods.Methods;

public class Task extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panelPrincipal, panelMenu, panelTasks, panelSearch;
	Search search;
	JButton btnAddTask;
	CustomLabel titleTask;
	JScrollPane panelTasksScroll;
	Methods methods = new Methods();
	String[] options = { "Titre", "Date de creation", "Date de modification" };
	List<TaskBean> defaultTask = new ArrayList<>();
	String sortTitle = "all";
	MenuOption selectedOption;

	public Task() {

		// JPanel
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelTasks = new JPanel();
		panelTasksScroll = new JScrollPane(panelTasks);

		for (int i = 1; i <= 20; i++) {
			String title = "#" + i + " Create a website ";
			String description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum "
					+ i;
			TaskBean task = new TaskBean(title, description);
			defaultTask.add(task);
		}

		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Task");
		setContentPane(panelPrincipal);
		init();
		initMenu();
		initListTask();
	}

	public void init() {
		JSplitPane splitPanelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panelMenu),
				panelTasksScroll);
		splitPanelPrincipal.setDividerLocation(350);

		Header header = new Header("GESTIONNAIRE DE TACHES");
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(header, BorderLayout.NORTH);
		panelPrincipal.add(splitPanelPrincipal, BorderLayout.CENTER);
	}

	public void initMenu() {
		search = new Search(enteredText -> {
			System.out.println("Entered Text: " + enteredText);
			setContentPanelTasks("Taches", searchTasksByDescription(defaultTask, enteredText));

		});
		search.requestFocusInWindow();

		JPanel panelContentMenu = new JPanel();
		panelContentMenu.setLayout(new BoxLayout(panelContentMenu, BoxLayout.Y_AXIS));
		methods.addPaddingPanel(panelContentMenu, 20, 0, 20, 0);
		panelContentMenu.setBackground(Color.WHITE);

		MenuOption option1 = new MenuOption("Importantes", 15);
		MenuOption option2 = new MenuOption("Terminées", 14);
		MenuOption option3 = new MenuOption("Planifiéess", 14);
		MenuOption option4 = new MenuOption("Taches", 14);

		option1.addMouseListener(new EcouteurMenuOption(() -> {
			List<TaskBean> taskList = new ArrayList<>();
			for (int i = 1; i <= 20; i++) {
				String title = "#" + i + " Create une template ";
				String description = "Lorem Ipsum has been the industry's standard dummy  make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum "
						+ i;
				TaskBean task = new TaskBean(title, description);
				taskList.add(task);
			}
			setContentPanelTasks("Importantes", taskList);
			setSelectedOption(option1);
		}));
		option2.addMouseListener(new EcouteurMenuOption(() -> {
			List<TaskBean> taskList = new ArrayList<>();
			for (int i = 1; i <= 20; i++) {
				String title = "#" + i + " Faire la lessive ";
				String description = "Lorem Ipsum has been the industry's standard dummy  et PageMaker including versions of Lorem Ipsum "
						+ i;
				TaskBean task = new TaskBean(title, description);
				taskList.add(task);
			}
			setContentPanelTasks("Terminées", taskList);
			setSelectedOption(option2);
		}));
		option3.addMouseListener(new EcouteurMenuOption(() -> {
			List<TaskBean> taskList = new ArrayList<>();
			for (int i = 1; i <= 20; i++) {
				String title = "#" + i + " Faire ces devoirs ";
				String description = "Lorem Ipsum has been the industry's standard dummy  et PageMaker including versions of Lorem Ipsum "
						+ i;
				TaskBean task = new TaskBean(title, description);
				taskList.add(task);
			}
			setContentPanelTasks("Planifiéess", taskList);
			setSelectedOption(option3);
		}));
		option4.addMouseListener(new EcouteurMenuOption(() -> {
			List<TaskBean> taskList = new ArrayList<>();
			setContentPanelTasks("Taches", taskList);
		}));

		panelContentMenu.add(option1);
		panelContentMenu.add(Box.createRigidArea(new Dimension(0, 20)));
		panelContentMenu.add(option2);
		panelContentMenu.add(Box.createRigidArea(new Dimension(0, 22)));
		panelContentMenu.add(option3);
		panelContentMenu.add(Box.createRigidArea(new Dimension(0, 20)));
		panelContentMenu.add(option4);
		panelContentMenu.add(Box.createRigidArea(new Dimension(0, 10)));

		CustomButton btnAddTask = new CustomButton("Ajouter une tache", 14);		
		btnAddTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateTaskDialog();
            }
        });
		CustomButton btnQuitTask = new CustomButton("Fermer", 14);

		JPanel panelBtnAddTask = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		panelBtnAddTask.setBackground(Color.WHITE);
		panelBtnAddTask.add(btnAddTask);
//		panelBtnAddTask.add(btnQuitTask);

		panelMenu.setLayout(new BorderLayout());
		methods.addPaddingPanel(panelMenu, 20, 20, 20, 20);
		panelMenu.add(search, BorderLayout.NORTH);
		panelMenu.add(panelContentMenu, BorderLayout.CENTER);
		panelMenu.add(panelBtnAddTask, BorderLayout.SOUTH);
		panelMenu.setBackground(Color.WHITE);

	}

	public void initListTask() {
		panelTasks.setBackground(Color.WHITE);
		panelTasks.setLayout(new BorderLayout());
		methods.addPaddingPanel(panelTasks, 20, 20, 20, 20);

		List<TaskBean> taskList = new ArrayList<>();
		for (int i = 1; i <= 20; i++) {
			String title = "#" + i + " Create a website ";
			String description = "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum "
					+ i;
			TaskBean task = new TaskBean(title, description);
			taskList.add(task);
		}

		setContentPanelTasks("Taches", taskList);

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
	
	

	public void setContentPanelTasks(String title, List<TaskBean> tasks) {

		panelTasks.removeAll();

		JPanel panelHeaderTask = new JPanel(new BorderLayout());
		panelHeaderTask.setBackground(Color.WHITE);
		CustomLabel titleTask = new CustomLabel(title, Font.BOLD, 24);
		panelHeaderTask.add(titleTask, BorderLayout.WEST);

		JComboBox<String> comboBox = new JComboBox<>(options);
		comboBox.setBackground(Color.white);

		JPanel panelSort = new JPanel(new FlowLayout(0, 15, 0));
		JLabel sortLable = new JLabel("Trie par");
		panelSort.add(sortLable);
		panelSort.add(comboBox);
		panelSort.setBackground(Color.white);

		panelHeaderTask.add(panelSort, BorderLayout.EAST);
		methods.addPaddingPanel(panelHeaderTask, 0, 0, 25, 0);

		JPanel panelContentTaks = new JPanel();
		panelContentTaks.setLayout(new BoxLayout(panelContentTaks, BoxLayout.Y_AXIS));
		panelContentTaks.setBackground(Color.white);

		ItemTask[] itmemTasks = new ItemTask[tasks.size()];
		for (int i = 0; i < itmemTasks.length; i++) {
			itmemTasks[i] = new ItemTask(tasks.get(i));
		}

		int i = 0;
		for (ItemTask item : itmemTasks) {
			item.addMouseListener(new EcouteurTask(() -> {
				quit();
			}));

			panelContentTaks.add(item);
			if (i % 2 == 0) {
				panelContentTaks.add(Box.createVerticalStrut(20));
			} else {
				panelContentTaks.add(Box.createVerticalStrut(25));
			}
			i++;

		}
		panelContentTaks.revalidate();
		panelContentTaks.repaint();

		panelTasks.add(panelHeaderTask, BorderLayout.NORTH);
		panelTasks.add(panelContentTaks, BorderLayout.CENTER);

		panelTasks.revalidate();
		panelTasks.repaint();

		scrollToTop(panelTasksScroll);

	}

	public void scrollToTop(JScrollPane scrollPane) {
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	    verticalScrollBar.setValue(0);
	    scrollPane.revalidate();
	    scrollPane.repaint();
	}

	private void openCreateTaskDialog() {
        CreateTaskDialog dialog = new CreateTaskDialog(this);
        dialog.setVisible(true);
    }


	public static List<TaskBean> searchTasksByDescription(List<TaskBean> taskList, String searchChar) {
		List<TaskBean> matchingTasks = new ArrayList<>();

		for (TaskBean task : taskList) {
			if (task.getDescription().contains(searchChar)) {
				matchingTasks.add(task);
			}
		}

		return matchingTasks;
	}

	public void quit() {

		int n = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter ?", "Quitter l 'application..",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION)
			System.exit(0);
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
