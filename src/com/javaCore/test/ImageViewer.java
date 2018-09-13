package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

class ImageViewerFrame extends JFrame{
	
	private JLabel label;
	private JFileChooser chooser;
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=400;
	
	public ImageViewerFrame() {
		setTitle("ImageView");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		label=new JLabel();
		add(label);
		
		chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu=new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem=new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				int result=chooser.showOpenDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					String name=chooser.getSelectedFile().getPath();
					label.setIcon(new ImageIcon(name));
				}
			}
		});
		menu.add(openItem);
		JMenuItem exitItem=new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		menu.add(exitItem);
	}
}
public class ImageViewer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame=new ImageViewerFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
