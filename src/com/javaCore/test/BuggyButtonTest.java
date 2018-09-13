package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class BuggyButtonFrame extends JFrame{
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	public BuggyButtonFrame() {
		setTitle("BuggyButtonTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		BuggyButtonPanel panel=new BuggyButtonPanel();
		add(panel);
	}
}
class BuggyButtonPanel extends JPanel{
	
	public BuggyButtonPanel() {
		ActionListener listener=new ButtonListener();
		JButton yellowButton=new JButton("Yellow");
		add(yellowButton);
		yellowButton.addActionListener(listener);
		
		JButton blueButton=new JButton("Blue");
		add(blueButton);
		blueButton.addActionListener(listener);
		
		JButton redButton=new JButton("Red");
		add(redButton);
		redButton.addActionListener(listener);
	}
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			String arg=event.getActionCommand();
			if(arg.equals("Yellow")) {
				setBackground(Color.YELLOW);
			}else if(arg.equals("Blue")) {
				setBackground(Color.BLUE);
			}else if(arg.equals("Red")) {
				setBackground(Color.RED);
			}
		}
		
	}
}
public class BuggyButtonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				BuggyButtonFrame frame=new BuggyButtonFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
