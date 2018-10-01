package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;
/**
 * This frame has a text area to show the contents of a text file, a menu to open a file and cancel
 * the opening process, and a status line to show the file loading progress.
 */
class SwingWorkerFrame extends JFrame{
	private JFileChooser chooser;
	private JTextArea textArea;
	private JLabel statueLine;
	private JMenuItem openItem;
	private JMenuItem cancelItem;
	private SwingWorker<StringBuilder, ProgressData> textReader;
	private static final int DEFAULT_WIDTH=450;
	private static final int DEFAULT_HEIGHT=350;
	
	public SwingWorkerFrame() {
		chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		
		textArea =new JTextArea();
		add(new JScrollPane(textArea));
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		statueLine=new JLabel("");
		add(statueLine,BorderLayout.SOUTH);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu=new JMenu("File");
		menuBar.add(menu);
		
		openItem =new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				// show file chooser dialog
				int result=chooser.showOpenDialog(null);
				// if file selected, set it as icon of the label
				if(result==JFileChooser.APPROVE_OPTION) {
					textArea.setText("");
					openItem.setEnabled(false);
					textReader=new TextReader(chooser.getSelectedFile());
					textReader.execute();
					cancelItem.setEnabled(true);
				}
			}
		});
		
		
		cancelItem=new JMenuItem("Cancel");
		menu.add(cancelItem);
		cancelItem.setEnabled(false);
		cancelItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				textReader.cancel(true);
			}
		});
	}
	private class ProgressData{
		private int number;
		private String line;
	}
	private class TextReader extends SwingWorker<StringBuilder, ProgressData>{
		private File file;
		private StringBuilder text=new StringBuilder();
		public TextReader(File file) {
			// TODO Auto-generated constructor stub
			this.file=file;
		}
		// the following method executes in the worker thread; it doesn't touch Swing components
		@Override
		public StringBuilder doInBackground() throws Exception {
			// TODO Auto-generated method stub
			int lineNumber=0;
			Scanner in=new Scanner(new FileInputStream(file));
			while(in.hasNextLine()) {
				String line=in.nextLine();
				lineNumber++;
				text.append(line);
				text.append("\n");
				ProgressData data=new ProgressData();
				data.number=lineNumber;
				data.line=line;
				publish(data);
				Thread.sleep(1);// to test cancellation; no need to do this in your programs
			}
			return text;
		}
		// the following methods execute in the event dispatch thread

		@Override
		public void process(List<ProgressData> data) {
			if(isCancelled()) return;
			StringBuilder b=new StringBuilder();
			statueLine.setText(""+data.get(data.size()-1).number);
			for(ProgressData d:data) {
				b.append(d.line);
				b.append("\n");
			}
			textArea.append(b.toString());
		}
		@Override
		public void done() {
			try {
				StringBuilder result=get();
				textArea.setText(result.toString());
				statueLine.setText("Done");
			} catch (InterruptedException ex) {
				// TODO: handle exception
			}catch (CancellationException ex) {
				// TODO: handle exception
				textArea.setText("");
				statueLine.setText("Cancelled");
			}catch (ExecutionException ex) {
				// TODO: handle exception
				statueLine.setText(""+ex.getCause());
			}
			cancelItem.setEnabled(false);
			openItem.setEnabled(true);
		}
	}
}
public class SwingWorkerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame=new SwingWorkerFrame();
				frame.setExtendedState(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
