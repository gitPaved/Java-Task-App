package com.task.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;

class StarPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color starColor = Color.BLACK;

	public StarPanel() {
		setPreferredSize(new Dimension(20, 20));
	}

	@Override
	public Dimension getPreferredSize() {
		int[] xPoints = { 10, 13, 18, 14, 15, 10, 5, 6, 2, 8 };
		int[] yPoints = { 2, 8, 8, 12, 16, 13, 16, 12, 8, 8 };

		int maxX = Arrays.stream(xPoints).max().orElse(0);
		int maxY = Arrays.stream(yPoints).max().orElse(0);

		return new Dimension(maxX, maxY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(starColor);

		int[] xPoints = { 10, 13, 18, 14, 15, 10, 5, 6, 2, 8 };
		int[] yPoints = { 2, 8, 8, 12, 16, 13, 16, 12, 8, 8 };

		g2d.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void setStarColor(Color color) {
		this.starColor = color;
		repaint();
	}
}
