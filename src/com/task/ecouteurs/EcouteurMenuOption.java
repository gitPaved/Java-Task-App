package com.task.ecouteurs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.task.components.MenuOption;
import com.task.interfaces.OnPressMenuOption;

public class EcouteurMenuOption implements MouseListener {

	OnPressMenuOption onPress;

	public EcouteurMenuOption(OnPressMenuOption onPress) {
		this.onPress = onPress;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (onPress != null) {
			onPress.execute();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		((MenuOption) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		((MenuOption) e.getSource()).setBackground(new Color(200, 200, 200));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		((MenuOption) e.getSource()).setBackground(Color.white);
	}

}
