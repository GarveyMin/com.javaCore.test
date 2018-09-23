package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * A runnable that animates a bouncing ball.
 */
class BallRunnable implements Runnable {
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 5;

	public BallRunnable(Ball aBall, Component aComponent) {
		// TODO Auto-generated constructor stub
		ball = aBall;
		component = aComponent;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for (int i = 1; i <= STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

class BounceFrameNum1 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BallComponent comp;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;

	/*
	 * Constructs the frame with the component for showing the bouncing ball and
	 * Start and Close buttons
	 */
	public BounceFrameNum1() {
		setSize(DEFAULT_WIDTH, DEFAULT_HIGHT);
		setTitle("BounceThread");
		comp = new BallComponent();
		add(comp, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
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

	/**
	 * Adds a button to a container.
	 * 
	 * @param c        the container
	 * @param title    the button title
	 * @param listener the action listener for the button
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}

	/*
	 * Adds a bouncing ball to the canvas and starts a thread to make it bounce
	 */
	public void addBall() {
		Ball b = new Ball();
		comp.add(b);
		Runnable r = new BallRunnable(b, comp);
		Thread t = new Thread(r);
		t.start();
	}
}

public class BounceThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BounceFrameNum1 frame = new BounceFrameNum1();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
