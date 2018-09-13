package com.javaCore.test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月23日下午1:02:02
 * @version 4.7.3a
 */
class SizedFrame extends JFrame {

	public SizedFrame() {
		// get screen dimension
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		// set frame width,height and let platform pick screen location
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
	}
}

public class SizedFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SizedFrame frame = new SizedFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
