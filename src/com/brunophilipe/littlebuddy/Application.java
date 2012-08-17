package com.brunophilipe.littlebuddy;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import com.brunophilipe.littlebuddy.screen.Canvas;
import com.brunophilipe.littlebuddy.screen.KeyPad;
import com.brunophilipe.littlebuddy.screen.Menu;

/**
 * @author Bruno Philipe
 * Main Application class.
 */
public class Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		app.initComponents();
		while (true) {
			canvas.repaint();
			Thread.sleep(30);
		}
	}
	
	private int distX,distY;
	private boolean dragging;
	private static boolean cursorHand = false;
	
	private void initComponents() {
		window = new JFrame();
		window.setTitle("LittleBuddy - InDev");
		window.setMinimumSize(new java.awt.Dimension(800,600));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		//window.setResizable(true);
		
		keypad = new KeyPad();
		
		String menuItems[] = {"feed","play","read","sleep","clean","medicine"};
		menu = new Menu(menuItems);
		
		window.addMouseMotionListener(keypad);
		window.addMouseMotionListener(new java.awt.event.MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragging) {
					int nx,ny;
					Point ml = java.awt.MouseInfo.getPointerInfo().getLocation();
					Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
					if ((ml == null)||(ss == null)) return;
					if (ml.x < ss.width - 50) {
						nx = ml.x - distX;
					} else {
						nx = window.getX();
					}
					if (ml.y < ss.height - 50) {
						ny = ml.y - distY;
					} else {
						ny = window.getY();
					}
					window.setLocation(nx, ny);
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				if (app.isMouseOnDragSpot()) {
					window.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
				} else if (cursorHand) {
					window.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					window.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
				}
			}
			
		});
		window.addMouseListener(keypad);
		window.addMouseListener(new java.awt.event.MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				if (app.isMouseOnDragSpot()) {
					Point ml = window.getMousePosition();
					distX = ml.x;
					distY = ml.y;
					dragging = true;
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dragging = false;
			}
			
		});
		com.sun.awt.AWTUtilities.setWindowOpaque(window, false);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new java.awt.Dimension(800,600));
		canvas.setLocation(0, 0);
		canvas.setVisible(true);
		canvas.setOpaque(false);
		
		window.add(canvas);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
	
	private boolean isMouseOnDragSpot() {
		Point ml = window.getMousePosition();
		return ((ml != null)&&(!dragging)&&(ml.x < 50)&&(ml.y<50));
	}
	
	public static void setHoverKey(int k) {
		canvas.setHoverKey(k);
		cursorHand = true;
	}
	
	public static void setSelectedMenuItem(int id) {
		menu.setSelectedMenuItem(id);
	}
	
	public static void increaseSelectedMenuItem() {
		menu.increaseSelectedMenuItem();
	}
	
	public static void decreaseSelectedMenuItem() {
		menu.decreaseSelectedMenuItem();
	}
	
	public static void clearHoverKey() {
		canvas.clearHoverKey();
		cursorHand = false;
	}
	
	public static boolean[][] getMenuScreen() {
		return menu.getMenuScreen();
	}
	
	private static Application app = new Application();
	private static JFrame window;
	private static Canvas canvas;
	private static KeyPad keypad;
	private static Menu menu;
}
