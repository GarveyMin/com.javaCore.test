package com.javaCore.test;


import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


class BounceFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int DEFAULT_WIDTH=450;
	public static final int DEFAULT_HEIGHT=350;
	public static final int STEPS=1000;
	public static final int DELAY=3;
	public BounceFrame() {
		// TODO Auto-generated constructor stub
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Bounce");
		
		comp=new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel=new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				addBall();
			}
		});
		addButton(buttonPanel, "Close", new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		add(buttonPanel, BorderLayout.SOUTH);
	}
	public void addButton(Container c,String title,ActionListener listener) {
		JButton button=new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	public void addBall() {
		try {
			Ball ball=new Ball();
			comp.add(ball);
			for(int i=1;i<=STEPS;i++) {
				ball.move(comp.getBounds());
				comp.paint(comp.getGraphics());
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
public class Bounce {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				BounceFrame frame=new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
