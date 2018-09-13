package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月26日上午11:51:26
 * @version 4.7.3a
 */
/*
 * A frame with a button panel
 */
class ButtonFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	private JPanel buttonPanel;

	public ButtonFrame() {
		setTitle("ButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// create button
		JButton yellowButton = new JButton("yellow");
		JButton blueButton = new JButton("blue");
		JButton redButton = new JButton("red");

		buttonPanel = new JPanel();
		// add buttons to buttonPanel
		buttonPanel.add(yellowButton);
		buttonPanel.add(blueButton);
		buttonPanel.add(redButton);
		// add buttonPanel to frame
		add(buttonPanel);
		// create button actions
		ColorAction yellowAction = new ColorAction(Color.YELLOW);
		ColorAction buleAction = new ColorAction(Color.BLUE);
		ColorAction redAction = new ColorAction(Color.RED);
		// Associate actions with button
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(buleAction);
		redButton.addActionListener(redAction);
	}

	/*
	 * An action listener that sets the panel's background color
	 */
	private class ColorAction implements ActionListener {

		private Color backgroundColor;

		public ColorAction(Color c) {
			// TODO Auto-generated constructor stub
			backgroundColor = c;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			buttonPanel.setBackground(backgroundColor);
		}

	}
}

public class ButtonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ButtonFrame frame = new ButtonFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
