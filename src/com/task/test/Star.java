package com.task.test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Star extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] xPoints = { 50, 65, 100, 75, 90, 50, 10, 25, 0, 35 };
	private int[] yPoints = { 10, 50, 50, 75, 110, 85, 110, 75, 50, 50 };

	Color borderColor, backgroundColor;
	int borderWidth;

	public Star(int borderWidth, Color borderColor, Color backgroundColor) {
		this.borderWidth = borderWidth;
		this.backgroundColor = backgroundColor;
		this.borderColor = borderColor;

//		setPreferredSize(new Dimension(50, 50));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(borderColor, borderWidth),
//				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		setBackground(backgroundColor);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(borderWidth));
//		g2d.drawPolygon(xPoints, yPoints, xPoints.length);
//		
//		Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(borderColor);

        int[] xPoints = {25, 30, 40, 32, 35, 25, 15, 18, 10, 20};
        int[] yPoints = {10, 20, 20, 30, 40, 32, 40, 30, 20, 20};

        g2d.fillPolygon(xPoints, yPoints, xPoints.length);
	}

	public void setBorderColor(Color color) {
		this.borderColor = color;
		repaint();
	}

	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
		setBackground(backgroundColor);
		repaint();
	}

	public void setBorderWidth(int width) {
		this.borderWidth = width;
		repaint();
	}

}
