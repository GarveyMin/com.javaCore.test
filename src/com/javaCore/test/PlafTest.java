package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
/**
 * 
 * @author Garvey Min
 * @date 2018年6月27日上午11:15:52
 * @version 4.7.3a
 */
/*
 * A frame with a button panel for changing look and feel
 */
class PlafFrame extends JFrame{
	private JPanel buttonPanel;
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	public PlafFrame() {
		setTitle("PlafTest");
		setSize(DEFAULT_WIDTH, DEFAULT_WIDTH);
		buttonPanel=new JPanel();
		
		UIManager.LookAndFeelInfo[] infos=UIManager.getInstalledLookAndFeels();
		for(UIManager.LookAndFeelInfo info:infos) {
			makeButton(info.getName(),info.getClassName());
			add(buttonPanel);
		}
	}
	/**
	 * Makes a button to change the pluggable look and feel
	 * @param name the button name
	 * @param plafName the name of the look and feel class
	 */
	void makeButton(String name,final String plafName) {
		//add button to panel
		JButton button=new JButton(name);
		buttonPanel.add(button);
		//set button action
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					UIManager.setLookAndFeel(plafName);
					SwingUtilities.updateComponentTreeUI(PlafFrame.this);
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
	}
}
public class PlafTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				PlafFrame frame=new PlafFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
