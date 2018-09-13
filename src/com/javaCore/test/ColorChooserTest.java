package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class ColorChooserFrame extends JFrame{
	
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	
	public ColorChooserFrame() {
		setTitle("ColorChooser");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		ColorChooserPanel panel=new ColorChooserPanel();
		add(panel);
	}
}
class ColorChooserPanel extends JPanel{
	
	public ColorChooserPanel() {
		JButton modalButton=new JButton("Modal");
		modalButton.addActionListener(new ModalListener());
		add(modalButton);
		
		JButton modelessButton=new JButton("Modeless");
		modalButton.addActionListener(new ModelessListener());
		add(modelessButton);
		
		JButton immedidateButton=new JButton("Immedidate");
		immedidateButton.addActionListener(new ImmedidateLinstener());
		add(immedidateButton);
	}
	private class ModalListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Color defaultColor=getBackground();
			Color selected=JColorChooser.showDialog(ColorChooserPanel.this,"set background", defaultColor);
			if(selected!=null) {
				setBackground(selected);
			}
		}
		
	}
	private class ModelessListener implements ActionListener{
		
		public JDialog dialog;
		public JColorChooser chooser;
		
		public ModelessListener() {
			// TODO Auto-generated constructor stub
			chooser=new JColorChooser();
			dialog=JColorChooser.createDialog(ColorChooserPanel.this, "Background Color", false, chooser, new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent event) {
					// TODO Auto-generated method stub
					setBackground(chooser.getColor());
				}
			}, null);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			chooser.setColor(getBackground());
			dialog.setVisible(true);
		}
		
	}
	private class ImmedidateLinstener implements ActionListener{
		
		public JDialog dialog;
		public JColorChooser chooser;
		
		public ImmedidateLinstener() {
			// TODO Auto-generated constructor stub
			chooser=new JColorChooser();
			chooser.getSelectionModel().addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent event) {
					// TODO Auto-generated method stub
					setBackground(chooser.getColor());
				}
			});
			dialog=new JDialog((JFrame)null, false);
			dialog.add(chooser);
			dialog.pack();
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			chooser.setBackground(getBackground());
			dialog.setVisible(true);
		}
		
	}
}
public class ColorChooserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ColorChooserFrame frame=new ColorChooserFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
