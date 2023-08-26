package com.task.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.task.components.CustomButton;
import com.task.components.Header;
import com.task.components.Search;
import com.task.methods.Methods;

public class Task2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	JPanel panelPrincipal, panelMenu, panelTasks, panelSearch;
	JLabel textMenu, textListTasks;
	Search search;
	JButton button1;
	Methods methods = new Methods();

	public Task2() {

		// Initialization of variable
		

		// JPanel
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelTasks = new JPanel();

		// JLabel
		textMenu = new JLabel("Menu");
		textMenu.setFont(new Font("Courier New", Font.BOLD, 12));

		textListTasks = new JLabel("Liste des taches");
		textListTasks.setFont(new Font("Courier New", Font.BOLD, 12));

		Border etchedBorder = BorderFactory.createEtchedBorder();
//		Border line = BorderFactory.createLineBorder(Color.DARK_GRAY,1);
		int paddingSize = 10;
//		panelMenu.setBorder( BorderFactory.createEmptyBorder(10,10,10,10));
        EmptyBorder paddingBorder = new EmptyBorder(paddingSize, paddingSize, paddingSize, paddingSize);
//        panelMenu.setBorder(line);
        
//        methods.addBorderedPanel(panelMenu, etchedBorder,paddingBorder);
//        methods.addBorderedPanel(panelTasks, etchedBorder,paddingBorder);
		

		// Initialization JFrame
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
		search.setPreferredSize(new Dimension(120,100));
		search.requestFocusInWindow();
		button1 = new JButton("Test");

		panelMenu.setLayout(new FlowLayout());
		panelMenu.add(textMenu);
		panelMenu.add(search);
//		panelMenu.setBackground(Color.WHITE);
		

	}

	public void initListTask() {
//		panelTasks.setBackground(Color.WHITE);

//		panelTasks.add(textListTasks);

		JPanel leftPanel = new JPanel(new FlowLayout(0, 15, 25));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));

		JButton button1 = new JButton("Bouton 1");
		JButton button2 = new JButton("Bouton 2");
		JButton button3 = new JButton("Bouton 3");

		leftPanel.add(button1);
		leftPanel.add(button2);
		leftPanel.add(button3);
		leftPanel.setBackground(Color.CYAN);

		JPanel panel11 = new JPanel();
//	        panel11.setLayout(new BoxLayout(panel11, BoxLayout.Y_AXIS));
		panel11.add(leftPanel);
		panel11.setBackground(Color.RED);

	        panelTasks.setLayout(new BoxLayout(panelTasks, BoxLayout.Y_AXIS));

//		panelTasks.setLayout(new FlowLayout());

		// Créez le premier sous-panneau avec BoxLayout (orientation verticale)
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

		JButton button12 = new JButton("Bouton 1");
		JButton button22 = new JButton("Bouton 2");

		panel1.add(button12);
		panel1.add(button22);

		panel1.setBackground(Color.GREEN);

		// Créez le deuxième sous-panneau avec BoxLayout (orientation verticale)
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

		JButton button33 = new JButton("Bouton 3");
		JButton button4 = new JButton("Bouton 4");

		panel2.add(button33);
		panel2.add(button4);
		panel2.setBackground(Color.RED);
		// Ajoutez les sous-panneaux au panneau principal
		panelTasks.add(leftPanel);

		panelTasks.add(Box.createVerticalStrut(20)); // Espacement vertical
		JLabel label1 = new JLabel("Élément 1");
		JLabel label2 = new JLabel("Élément 2");
		panelTasks.add(label1);
		panelTasks.add(label2);
		
		
		
		search = new Search(enteredText -> {
			System.out.println("Entered Text: " + enteredText);
		});
		search.requestFocusInWindow();

		
		
		CustomButton btnAddTask = new CustomButton("Ajouter une tache", 14);
		CustomButton btnQuitTask = new CustomButton("Fermer", 14);
		
		JPanel panelBtnAddTask = new JPanel();
		panelBtnAddTask.setLayout(new BoxLayout(panelBtnAddTask, BoxLayout.X_AXIS));
		panelBtnAddTask.setBackground(Color.WHITE);
		panelBtnAddTask.add(search);
		panelBtnAddTask.add(Box.createHorizontalStrut(15));
		panelBtnAddTask.add(btnAddTask);
		panelBtnAddTask.add(Box.createHorizontalStrut(15));
		panelBtnAddTask.add(btnQuitTask);
		
		panelTasks.add(panelBtnAddTask);
		panelTasks.add(Box.createVerticalStrut(15));
		panelTasks.add(panel1);
		panelTasks.add(Box.createVerticalStrut(15));
		panelTasks.add(panel2);
		panelTasks.setBackground(Color.WHITE);

//		panelTasks.setPreferredSize(new Dimension(500, 700));
	}


	public void init() {
//		panelPrincipal.setBackground(Color.WHITE);
//		panelPrincipal.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				panelPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//			}
//
//			@Override
//			public void mouseExited(MouseEvent e) {
//				panelPrincipal.setCursor(Cursor.getDefaultCursor());
//			}
//		});
		
		
		
		JSplitPane splitPanelPrincipal = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(panelMenu),new JScrollPane(panelTasks));
		splitPanelPrincipal.setDividerLocation(350);
		
		Header header = new Header("GESTIONNAIRE DE TACHES");
		
		
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.add(header,BorderLayout.NORTH);
		panelPrincipal.add(splitPanelPrincipal,BorderLayout.CENTER);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Task2().setVisible(true);
			}
		});
	}

}
