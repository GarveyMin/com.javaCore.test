package com.javaCore.test;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

class ButtonPanel1 extends JPanel{
	
	public ButtonPanel1() {
		//create buttons
		JButton yellowButton=new JButton("Yellow");
		JButton blueButton=new JButton("Blue");
		JButton redButton=new JButton("Red");
		//add buttons to panel
		add(yellowButton);
		add(blueButton);
		add(redButton);
		//create buttons action
		ColorAction yellowAction=new ColorAction(Color.YELLOW);
		ColorAction blueAction=new ColorAction(Color.BLUE);
		ColorAction redAction=new ColorAction(Color.RED);
		//association actions with buttons 
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
	}
	/*
	 * An action listener that sets the panel's background color
	 */
	private class ColorAction implements ActionListener{
		private Color backgroundColor;
		
		public ColorAction(Color c) {
			// TODO Auto-generated constructor stub
			backgroundColor=c;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			setBackground(backgroundColor);
		}
		
	}
}
