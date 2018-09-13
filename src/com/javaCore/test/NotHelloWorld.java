package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月23日下午1:16:29
 * @version 4.7.3a
 */
// A frame that contains a message panel
class NotHelloWorldFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEITHT = 200;

	public NotHelloWorldFrame() {
		setTitle("NotHelloWorld");
		setSize(DEFAULT_WIDTH, DEFAULT_HEITHT);
		// add panel to frame
		NotHelloWorldPanel panel = new NotHelloWorldPanel();
		add(panel);

	}
}

/**
 * 
 * A panel that displays a message
 */
class NotHelloWorldPanel extends JPanel {

	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;

	public void paintComponent(Graphics g) {
		g.drawString("Not a Hello,World program", MESSAGE_X, MESSAGE_Y);
	}

}

public class NotHelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				NotHelloWorldFrame frame = new NotHelloWorldFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
