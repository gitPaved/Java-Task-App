package com.task.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;


public class Task extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Variables
	JPanel panelPrincipal, panelMenu, panelTasks, panelSearch;
	JLabel textMenu, textListTasks;
	Search search;
	JButton button1;

	public Task() {

		// Initialization of variable

		// JPanel
		panelPrincipal = new JPanel();
		panelMenu = new JPanel();
		panelTasks = new JPanel();
		panelTasks = new JPanel();

		// JLabel
		textMenu = new JLabel("Menu");
		textMenu.setFont(new Font("Courier New", Font.BOLD, 12));

		textListTasks = new JLabel("Liste des taches");
		textListTasks.setFont(new Font("Courier New", Font.BOLD, 12));

		Border etchedBorder = BorderFactory.createEtchedBorder();
		addBorderedPanel(panelMenu, etchedBorder);
		addBorderedPanel(panelTasks, etchedBorder);

		// Initialization JFrame
		setSize(1200, 700);
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
		button1 = new JButton("Test");

		panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
		panelMenu.add(textMenu);
		panelMenu.add(search);
		panelMenu.setBackground(Color.WHITE);
		

	}

	public void initListTask() {
		panelTasks.setBackground(Color.WHITE);
		panelTasks.add(textListTasks);
		panelTasks.setPreferredSize(new Dimension(500, 700));
	}

	public void init() {
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panelPrincipal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelPrincipal.setCursor(Cursor.getDefaultCursor());
			}
		});
		panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.X_AXIS));
		panelPrincipal.add(panelMenu, BorderLayout.NORTH);
		panelPrincipal.add(panelTasks, BorderLayout.SOUTH);
	}

	public void addBorderedPanel(JPanel panel, Border border) {
		panel.setBorder(border);
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
