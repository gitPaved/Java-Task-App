package com.task.methods;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.task.components.RoundedBorder;

public class Methods {

	public Methods() {
	}

	public void addBorderedPanel(JPanel panel, Border border, Border paddingBorder) {
		Border compoundBorder = BorderFactory.createCompoundBorder(border, paddingBorder);
		panel.setBorder(compoundBorder);
	}

	public void addPaddingBtn(JButton b, int h, int g, int i, int r) {
		b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
				BorderFactory.createEmptyBorder(h, g, i, r)));
	}

	public void addPaddingPanel(JPanel p, int h, int g, int i, int r) {
		p.setBorder(BorderFactory.createEmptyBorder(h, g, i, r));
	}
	
	public void addRounderAndPaddingPanel(JPanel p, int radius,Color color ,int h, int g, int i, int r) {
		p.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(radius,color,0),
				BorderFactory.createEmptyBorder(h, g, i, r)));
	}

}
