package com.brunophilipe.littlebuddy.screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import com.brunophilipe.littlebuddy.screen.menu.Menu;

/**
 * @author Bruno Philipe
 * Game rendering screen class.
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {
	
	//private static Rectangle2D.Double toyScreen = new Rectangle2D.Double(60,60,580,430);
	private static Rectangle2D.Double auxBox = new Rectangle2D.Double();
	
	private static Image imgBack = Toolkit.getDefaultToolkit().getImage(Canvas.class.getResource("res/littlebuddy.png"));
	private static Image imgBtUP = Toolkit.getDefaultToolkit().getImage(Canvas.class.getResource("res/bt_up.png"));
	private static Image imgBtDOWN = Toolkit.getDefaultToolkit().getImage(Canvas.class.getResource("res/bt_down.png"));
	private static Image imgBtOK = Toolkit.getDefaultToolkit().getImage(Canvas.class.getResource("res/bt_ok.png"));
	private static Image imgBtMENU = Toolkit.getDefaultToolkit().getImage(Canvas.class.getResource("res/bt_menu.png"));
	
	private int i,j,hoverKey = -1;
	private double w,h;
	
	private static Color colorScreen = new Color(158,182,165);
	private static Color colorImage = new Color(11,11,11);
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawImage(imgBack, 0, 0, 800, 600, this);
		g2.setColor(colorScreen);
		
		w = 6;
		h = 6;
		
		boolean[][] screen = Menu.getInstance().getMenuScreen();
		
		for (j=0;j<72;j++) {
			for (i=0;i<97;i++) {
				auxBox.setFrame(60 + (w*i),60 + (h*j), w-1, h-1);
				if (screen[i][j]){
					g2.setColor(colorImage);
				} else {
					g2.setColor(colorScreen);
				}
				g2.fill(auxBox);
			}
		}
		
		if (hoverKey >= 0) {
			g2.drawImage(getBtImage(hoverKey), 680, getBtHeight(hoverKey), 90, 90, this);
		}
	}
	
	private int getBtHeight(int k) {
		switch (k) {
		case 0:
			return 50;
		case 1:
			return 170;
		case 2:
			return 290;
		case 3:
			return 410;
		}
		return 0;
	}
	
	private Image getBtImage(int k) {
		switch (k) {
		case 0:
			return imgBtUP;
		case 1:
			return imgBtDOWN;
		case 2:
			return imgBtOK;
		case 3:
			return imgBtMENU;
		}
		return null;
	}
	
	public void setHoverKey(int k) {
		hoverKey = k;
	}
	
	public void clearHoverKey() {
		hoverKey = -1;
	}
}
