package com.brunophilipe.littlebuddy.utility;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageToMatrix {
	
	public static void main(String ... args) {
		Image resource = null;
		try {
			javax.swing.UIManager.setLookAndFeel(
			javax.swing.UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {}
		try {
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "Images", "jpg", "gif", "png");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	resource = ImageIO.read(chooser.getSelectedFile());
		    } else {
		    	return;
		    }
		} catch (Exception ex) {
			return;
		}
		BufferedImage item = toBufferedImage(resource);
		
		int x,y;
		char print;
		
		for (y=0;y<10;y++) {
			System.out.print("composition[" + y + "] = \"");
			for (x=0;x<56;x++) {
				print = ((item.getRGB(x, y) == -1) ? '8' : '_');
				System.out.print(print);
			}
			System.out.println("\";");
		}
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
