package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class ConsoleWindowFrame extends JFrame{
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	
	public ConsoleWindowFrame() {
		setTitle("ConsoleWindow");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		ButtonPanel panel=new ButtonPanel();
		add(panel);
		
	}
	
}
class ButtonPanel extends JPanel{
	
	public ButtonPanel() {
		
		JButton yellowButton=new JButton("Yellow");
		JButton blueButton=new JButton("Blue");
		JButton redButton=new JButton("Red");
		
		add(yellowButton);
		add(blueButton);
		add(redButton);
		
		ColorAction yellowAction=new ColorAction(Color.YELLOW);
		ColorAction blueAction=new ColorAction(Color.BLUE);
		ColorAction redAction=new ColorAction(Color.RED);
		
		yellowButton.addActionListener(yellowAction);
		blueButton.addActionListener(blueAction);
		redButton.addActionListener(redAction);
		
		ConsoleWindow.init();
		
	}
	private class ColorAction implements ActionListener{
		
		private Color background;
		
		public ColorAction(Color c) {
			// TODO Auto-generated constructor stub
			background=c;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			System.out.println(event);
			setBackground(background);
		}
		
	}
}
public class ConsoleWindowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ConsoleWindowFrame frame=new ConsoleWindowFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
