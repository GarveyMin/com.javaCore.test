package com.javaCore.test;

import javax.swing.JFrame;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月23日下午12:27:57
 * @version 4.7.3a
 */
class SimpleFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;

	public SimpleFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}

public class SimpleFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleFrame frame = new SimpleFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
