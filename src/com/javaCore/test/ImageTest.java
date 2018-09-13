package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月26日上午10:11:03
 * @version 4.7.3a
 */
/*
 * A frame with an image component
 */
class ImageFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	public ImageFrame() {
		setTitle("ImageTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// add component to frame
		ImageComponent component = new ImageComponent();
		add(component);
	}

}

/*
 * A component that display a tiled image
 */
class ImageComponent extends JComponent {

	private Image image;

	public ImageComponent() {
		// acquire the image
		try {
			image = ImageIO.read(new File("G:\\Dreamweaver\\image\\new1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paintComponent(Graphics g) {
		if (image == null) {
			return;
		}
		int imageWidth = image.getWidth(this);
		int imageHeight = image.getHeight(this);
		// draw the image in the top-left corner
		g.drawImage(image, 0, 0, null);
		// tile the image across the component
		for (int i = 0; i * imageWidth <= getWidth(); i++) {
			for (int j = 0; j * imageHeight <= getHeight(); i++) {
				if (i + j > 0) {
					g.copyArea(0, 0, imageWidth, imageHeight, i * imageWidth, j * imageHeight);
				}
			}
		}
	}
}

public class ImageTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ImageFrame frame = new ImageFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
