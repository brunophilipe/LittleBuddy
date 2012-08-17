package com.brunophilipe.littlebuddy.screen;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class MenuItem {
	private Boolean composition[][];
	
	public static void main(String args[]) {
		MenuItem menuItem = new MenuItem("sleep");
		int x,y;
		char print;
		
		for (y=0;y<10;y++) {
			for (x=0;x<56;x++) {
				print = (menuItem.composition[x][y] ? '█' : '░');
				System.out.print(print);
			}
			System.out.println();
		}
	}
	
	public MenuItem(String image) {
		String values[] = new String[10];
		Image resource;
		try {
			resource = Toolkit.getDefaultToolkit().getImage(MenuItem.class.getResource("res/menu/" + image + ".png"));
		} catch (java.lang.NullPointerException ex) {
			return;
		}
		BufferedImage item = toBufferedImage(resource);
		
		int x,y;
		char curLine[];
		composition = new Boolean[56][10];
		
		for (y=0;y<10;y++) {
			curLine = new char[56];
			for (x=0;x<56;x++) {
				composition[x][y] = (item.getRGB(x, y) == -1);
			}
			values[y] = new String(curLine);
		}
	}
	
	public Boolean[][] getMap() {
		return composition;
	}
	
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	    }

	    // This code ensures that all the pixels in the image are loaded
	    image = new ImageIcon(image).getImage();

	    // Create a buffered image with a format that's compatible with the screen
	    BufferedImage bimage = null;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.OPAQUE;

	        // Create the buffered image
	        GraphicsDevice gs = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gs.getDefaultConfiguration();
	        bimage = gc.createCompatibleImage(
	            image.getWidth(null), image.getHeight(null), transparency);
	    } catch (HeadlessException e) {
	        // The system does not have a screen
	    }

	    if (bimage == null) {
	        // Create a buffered image using the default color model
	        int type = BufferedImage.TYPE_INT_RGB;
	        bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
	    }

	    // Copy image to buffered image
	    Graphics g = bimage.createGraphics();

	    // Paint the image onto the buffered image
	    g.drawImage(image, 0, 0, null);
	    g.dispose();

	    return bimage;
	}
}
