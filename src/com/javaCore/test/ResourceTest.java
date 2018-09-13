package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextArea;

class ResourceTestFrame extends JFrame{
	
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=300;
	
	public ResourceTestFrame() {
		setTitle("ResourceTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		URL aboutURL=getClass().getResource("about.gif");
		Image img=Toolkit.getDefaultToolkit().getImage(aboutURL);
		setIconImage(img);
		
		JTextArea textArea=new JTextArea();
		InputStream stream=getClass().getResourceAsStream("D:/about.txt");
		Scanner in=new Scanner(stream);
		while(in.hasNext()) 
			textArea.append(in.nextLine()+"\n");
			add(textArea);
	}
}
public class ResourceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ResourceTestFrame frame=new ResourceTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
