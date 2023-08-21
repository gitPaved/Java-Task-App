package com.task.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {

    public MainApp() {
        setTitle("Gestionnaire de Tâches");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton createTaskButton = new JButton("Créer une Nouvelle Tâche");

        createTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCreateTaskDialog();
            }
        });

        JPanel panel = new JPanel();
        panel.add(createTaskButton);

        add(panel);
    }

    private void openCreateTaskDialog() {
        CreateTaskDialog dialog = new CreateTaskDialog(this);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}
