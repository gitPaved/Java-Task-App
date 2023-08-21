package com.task.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateTaskDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CreateTaskDialog(Frame owner) {
        super(owner, "Créer une Nouvelle Tâche", false); 
        setSize(300, 200);

        JPanel panel = new JPanel();
        JLabel nameLabel = new JLabel("Nom de la Tâche:");
        JTextField nameField = new JTextField(20);
        JButton createButton = new JButton("Créer");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String taskName = nameField.getText();
                dispose();
            }
        });

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(createButton);

        add(panel);
    }
}

