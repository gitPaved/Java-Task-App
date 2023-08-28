package com.task.methods;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	public void addRounderAndPaddingPanel(JPanel p, int radius, Color color, int h, int g, int i, int r) {
		p.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(radius, color, 0),
				BorderFactory.createEmptyBorder(h, g, i, r)));
	}

	public Date convertStingToDate(String dateString, String pattern) {

		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		try {
			if (dateString != null) {
				Date date = dateFormat.parse(dateString);
				return date;
			}
		} catch (ParseException e) {
//			System.err.println("Erreur lors de la conversion de la chaîne en date : " + e.getMessage());
			return null;
		}
		return null;
	}

	public String convertDateToString(Date date, String pattern) {
		String dateString = "";
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateString = dateFormat.format(date);
//			System.out.println("Date convertie en chaîne : " + dateString);

		}
		return dateString;
	}

}
