package com.javaCore.test;

import javax.swing.JFrame;

class ButtonFrames extends JFrame{
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	public ButtonFrames() {
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//add panel to frame
		ButtonPanel1 panel=new ButtonPanel1();
		add(panel);
	}
}
