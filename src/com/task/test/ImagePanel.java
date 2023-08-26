package com.task.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Image image;

	public ImagePanel(String imagePath) {
		this.setPreferredSize(new Dimension(20, 20));
		this.setBackground(Color.WHITE);
		try {
			image = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		}
	}
}
