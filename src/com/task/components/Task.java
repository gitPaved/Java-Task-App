package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.task.bean.TaskBean;
import com.task.ecouteurs.EcouteurMenuOption;
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
	Methods methods = new Methods();

	public Task() {

		// JPanel
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelTasks = new JPanel();

		setSize(1000, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Task");
		setContentPane(panelPrincipal);
		initMenu();
		initListTask();
		init();
	}

	public void initMenu() {
		search = new Search(enteredText -> {
			System.out.println("Entered Text: " + enteredText);
		});
		search.requestFocusInWindow();

		JPanel panelContentMenu = new JPanel();
		panelContentMenu.setLayout(new BoxLayout(panelContentMenu, BoxLayout.Y_AXIS));
		methods.addPaddingPanel(panelContentMenu, 20, 0, 20, 0);
		panelContentMenu.setBackground(Color.WHITE);

		MenuOption option1 = new MenuOption("Importantes", 15);
		MenuOption option2 = new MenuOption("Terminees", 14);
		MenuOption option3 = new MenuOption("Planifiees", 14);
		MenuOption option4 = new MenuOption("Taches", 14);

		option1.addMouseListener(new EcouteurMenuOption(() -> {
			quit();
		}));
		option2.addMouseListener(new EcouteurMenuOption(() -> {
			quit();
		}));
		option3.addMouseListener(new EcouteurMenuOption(() -> {
			quit();
		}));
		option4.addMouseListener(new EcouteurMenuOption(() -> {
			quit();
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
		CustomButton btnQuitTask = new CustomButton("Fermer", 14);

		JPanel panelBtnAddTask = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
		panelBtnAddTask.setBackground(Color.WHITE);
		panelBtnAddTask.add(btnAddTask);
		panelBtnAddTask.add(btnQuitTask);

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

		JPanel panelHeaderTask = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelHeaderTask.setBackground(Color.WHITE);
		titleTask = new CustomLabel("Taches", Font.BOLD, 24);
		panelHeaderTask.add(titleTask);
		methods.addPaddingPanel(panelHeaderTask, 0, 0, 30, 0);

		JPanel panelContentTaks = new JPanel();
		panelContentTaks.setLayout(new BoxLayout(panelContentTaks, BoxLayout.Y_AXIS));

		ItemTask[] itmemTasks = { new ItemTask(new TaskBean("Creer un site web",
				"Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum")) };
		int i = 0;
		for (ItemTask item : itmemTasks) {
//			item.addMouseListener(new EcouteurMenuOption(() -> {
//				quit();
//			}));

			panelContentTaks.add(item);
			if (i % 2 == 0) {
				panelContentTaks.add(Box.createRigidArea(new Dimension(0, 20)));
			} else {
				panelContentTaks.add(Box.createRigidArea(new Dimension(0, 22)));
			}
			i++;

		}

		panelTasks.add(panelHeaderTask, BorderLayout.NORTH);
		panelTasks.add(panelContentTaks, BorderLayout.CENTER);

	}

	public void init() {
		
		JPanel test = new JPanel();
		
		JScrollPane panelTasksScrollPane = new JScrollPane(panelTasks);
		test.add(panelTasksScrollPane);
//		panelTasksScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		panelTasksScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		JSplitPane splitPanelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(panelMenu),
				test);
		splitPanelPrincipal.setDividerLocation(350);

		Header header = new Header("GESTIONNAIRE DE TACHES");

		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(header, BorderLayout.NORTH);
		panelPrincipal.add(splitPanelPrincipal, BorderLayout.CENTER);
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
