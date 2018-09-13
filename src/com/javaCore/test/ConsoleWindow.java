package com.javaCore.test;

import java.awt.Frame;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleWindow{
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	public static final int DEFAULT_LEFT=200;
	public static final int DEFAULT_TOP=200;
	
	public static void init() {
		JFrame frame=new JFrame();
		frame.setTitle("ConsoleWindow");
		final JTextArea output=new JTextArea();
		output.setEditable(false);
		frame.add(new JScrollPane(output));
		frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		frame.setLocation(DEFAULT_LEFT, DEFAULT_TOP);
		frame.setFocusableWindowState(false);
		frame.setVisible(true);
		
		PrintStream consoleStream=new PrintStream(new OutputStream() {
			
			@Override
			public void write(int arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
			public void write(byte[] b,int off,int len) {
			output.append(new String(b, off, len));	
			}
		});
		System.setOut(consoleStream);
		System.setErr(consoleStream);
	}
}
