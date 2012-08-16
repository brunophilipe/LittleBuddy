package com.brunophilipe.littlebuddy.screen;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.brunophilipe.littlebuddy.Application;

public class KeyPad implements MouseListener, MouseMotionListener {
	
	private Rectangle area = new Rectangle(680,50,90,450);
	
	public KeyPad() {}

	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
		if ((area.contains(e.getPoint()))&&(getButton(e.getY()) > -1)) {
			Application.setHoverKey(getButton(e.getY()));
		} else {
			Application.clearHoverKey();
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
//		if ((area.contains(e.getPoint()))&&(getButton(e.getY()) > -1)) {
//			System.out.println(getButton(e.getY()));
//		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if ((area.contains(e.getPoint()))&&(getButton(e.getY()) > -1)) {
			switch (getButton(e.getY())) {
			case 0:case 1:	
				Application.setDirection(1);
				break;
			case 2:
				Application.setDirection(-1);
				break;
			case 3:
				Application.setDirection(0);
				break;
			}
		}
	}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
	private int getButton(int y) {
		if (y < 140) {
			return 0;
		} else if ((y < 260)&&(y > 170)) {
			return 1;
		} else if ((y < 380)&&(y > 290)) {
			return 2;
		} else if ((y < 500)&&(y > 410)) {
			return 3;
		} else return -1;
	}
}
