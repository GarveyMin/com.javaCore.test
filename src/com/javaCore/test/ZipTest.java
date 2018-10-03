package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

class ZipTestFrame extends JFrame{
	public static final int DEFAULT_WIDTH=400;
	public static final int DEFAULT_HEIGHT=300;
	
	private JComboBox fileCome;
	private JTextArea fileText;
	private String zipname;
	
	public ZipTestFrame() {
		setTitle("ZipTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenuBar meunBar=new JMenuBar();
		JMenu menu=new JMenu("File");
		JMenuItem openItem=new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				JFileChooser chooser=new JFileChooser();
				chooser.setCurrentDirectory(new File("."));
				int r=chooser.showOpenDialog(ZipTestFrame.this);
				if(r==JFileChooser.APPROVE_OPTION) {
					zipname=chooser.getSelectedFile().getPath();
					fileCome.removeAllItems();
					scanZipFile();
				}
			}
		});
		
		JMenuItem exitItem=new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		meunBar.add(menu);
		setJMenuBar(meunBar);
		
		fileText=new JTextArea();
		fileCome=new JComboBox();
		fileCome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				loadZipFile((String) fileCome.getSelectedItem());
			}
		});
		add(fileCome,BorderLayout.SOUTH);
		add(new JScrollPane(fileText),BorderLayout.CENTER);
	}
	public void scanZipFile() {
		new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
				ZipEntry entry;
				while((entry=zin.getNextEntry())!=null)  {
					publish(entry.getName());
					zin.closeEntry();
				}
				zin.close();
				return null;
			}
			protected void process(List<String> names) {
				 for (String name : names) {
					fileCome.addItem(name);
				}
			}
		}.execute();
	}
	public void loadZipFile(final String name) {
		fileCome.setEditable(false);
		fileText.setText("");
		new SwingWorker<Void, Void>(){

			@Override
			protected Void doInBackground() throws Exception {
				// TODO Auto-generated method stub
				try {
					ZipInputStream zin=new ZipInputStream(new FileInputStream(zipname));
					ZipEntry entry;
					while((entry=zin.getNextEntry())!=null) {
						if(entry.getName().equals(name)) {
							Scanner in=new Scanner(zin);
							while(in.hasNextLine()) {
								fileText.append(in.nextLine());
								fileText.append("\n");
							}
						}
						zin.closeEntry();
					}
					zin.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return null;
			}
			protected void done() {
				fileCome.setEditable(true);
			}
		}.execute();
	}
}
public class ZipTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ZipTestFrame frame=new ZipTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
