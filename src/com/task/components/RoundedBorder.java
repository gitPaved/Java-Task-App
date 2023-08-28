package com.task.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int cornerRadius,margin;
	Color color;

	public RoundedBorder(int cornerRadius, Color color, int margin) {
		this.cornerRadius = cornerRadius;
		this.color = color;
		this.margin = margin; 
	}
	
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Shape borderShape = new RoundRectangle2D.Double(x+margin, y+margin, width - 1, height - 1, cornerRadius, cornerRadius);
//		Shape borderShape = new RoundRectangle2D.Double(x + margin, y + margin, width - 1 - 2 * margin,
//				height - 1 - 2 * margin, cornerRadius, cornerRadius);
		g2.setColor(color);
		g2.draw(borderShape);
	}
}
