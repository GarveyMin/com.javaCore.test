package com.javaCore.test;

import java.applet.Applet;
import java.awt.EventQueue;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NotHelloWorldApplet extends JApplet{
	
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JLabel label=new JLabel("Not a Hello,Wrold Applet",SwingConstants.CENTER);
				add(label);
			}
		});
	}
}
