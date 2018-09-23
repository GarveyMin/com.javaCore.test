package com.javaCore.test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileFilter;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileView;

class FileChooserFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	private JFileChooser chooser;
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=400;
	
	public FileChooserFrame() {
		setTitle("FileChooeser");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu=new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem openItem=new JMenuItem("Open");
		openItem.addActionListener(new FileOpenListener());
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
		label=new JLabel();
		add(label);
		
		chooser=new JFileChooser();
		FileNameExtensionFilter filter=new FileNameExtensionFilter("Image files", "jpg","jpeg","gif");
		chooser.setFileFilter(filter);
		chooser.setAccessory(new ImagePreviewer(chooser));
		chooser.setFileView(new FileIconView(filter, new ImageIcon("palette.gif")));
	}
	private class FileOpenListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			int result=chooser.showOpenDialog(FileChooserFrame.this);
			if(result==JFileChooser.APPROVE_OPTION){
				String name=chooser.getSelectedFile().getPath();
				label.setIcon(new ImageIcon(name));
			}
		}
		
	}
	
}
class FileIconView extends FileView{
	private javax.swing.filechooser.FileFilter filter;
	private Icon icon;
	
	public FileIconView(javax.swing.filechooser.FileFilter aFilter,Icon anIcon) {
		filter=aFilter;
		icon=anIcon;
		
	}
	public Icon getIcon(File f) {
		if(!f.isDirectory()&&filter.accept(f)) {
			return icon;
		}else {
			return null;
		}
	}
}
class ImagePreviewer extends JLabel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImagePreviewer(JFileChooser chooser) {
		setPreferredSize(new Dimension(100, 100));
		setBorder(BorderFactory.createEtchedBorder());
		chooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
					File f=(File) event.getNewValue();
					if(f==null) {
						setIcon(null);
						return;
					}
					ImageIcon icon=new ImageIcon(f.getPath());
					if(icon.getIconWidth()>getWidth())icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(), -1, Image.SCALE_DEFAULT)); {
						setIcon(icon);
					}
				}
			}
		});
	}
}
public class FileChooserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				FileChooserFrame frame=new FileChooserFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
