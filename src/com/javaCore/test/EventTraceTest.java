package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;

class EventTraceFrame extends JFrame{
	public static final int DEFAULT_WIDTH=400;
	public static final int DEFAULT_HEIGHT=400;
	
	public EventTraceFrame() {
		setTitle("EventTraceTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//add a slider and a button
		add(new JSlider(),BorderLayout.NORTH);
		add(new JButton("Test"), BorderLayout.SOUTH);
		//trap all events of components inside the frame
		EventTracer tracer=new EventTracer();
		tracer.add(this);
	}
}
public class EventTraceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame=new EventTraceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
