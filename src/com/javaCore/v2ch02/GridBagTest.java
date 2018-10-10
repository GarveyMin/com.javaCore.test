package com.javaCore.v2ch02;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;

class FontFrame extends JFrame{
	
	private GridBagPane gridbag;
	private JComboBox face;
	private JComboBox size;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int DEFAULT_WIDTH=400;
	private static final int DEFAULT_HEIGHT=400;
	
	public FontFrame(String filename) {
		setTitle("GridBagTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		gridbag=new GridBagPane(filename);
		add(gridbag);
		face=(JComboBox) gridbag.get("face");
		size=(JComboBox) gridbag.get("size");
		bold=(JCheckBox) gridbag.get("bold");
		italic=(JCheckBox) gridbag.get("italic");
		
		face.setModel(new DefaultComboBoxModel(new Object[] {"Serif","SansSerif", "Monospaced","Dialog", 
				"DialogInput"}));
		size.setModel(new DefaultComboBoxModel<>(new Object[] {"8", "10", "12", "15", "18", "24",
				"36", "48"}));
		
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setSample();
			}
		};
		face.addActionListener(listener);
		size.addActionListener(listener);
		bold.addActionListener(listener);
		italic.addActionListener(listener);
		setSample();
	}
	
	public void setSample() {
		String fontFace=(String) face.getSelectedItem();
		int fontSize=Integer.parseInt((String) size.getSelectedItem());
		JTextArea sample=(JTextArea) gridbag.get("sample");
		int fontStyle=(bold.isSelected()? Font.BOLD:0)+(italic.isSelected() ? Font.ITALIC:0);
		sample.setFont(new Font(fontFace, fontStyle, fontSize));
		sample.repaint();
	}
}
public class GridBagTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String filename=args.length==0 ? "fontdialog.xml":args[0];
			}
		});
	}

}
