package com.task.ecouteurs;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.task.components.ItemTask;
import com.task.interfaces.OnPressTask;

public class EcouteurTask implements MouseListener {

	OnPressTask onPress;

	public EcouteurTask(OnPressTask onPress) {
		this.onPress = onPress;
	}
	
	public EcouteurTask() {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (onPress != null) {
			ItemTask task = (ItemTask) e.getSource();
			onPress.execute(task.getTaskBean(),"");
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
		((ItemTask) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		((ItemTask) e.getSource()).setBackground(new Color(200, 200, 200));
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		((ItemTask) e.getSource()).setBackground(Color.white);
	}

}
