package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.JobAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.DoubleConsumer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class XPathframe extends JFrame{
	
	private DocumentBuilder builder;
	private Document doc;
	private XPath path;
	private JTextField expression;
	private JTextField result;
	private JTextArea docText;
	private JComboBox typeCombo;
	
	public XPathframe() {
		
		setTitle("XPathTest");
		setSize(WIDTH, HEIGHT);
		JMenu fileMenu=new JMenu("File");
		JMenuItem openItem=new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				openFile();
			}
		});
		fileMenu.add(openItem);
		
		
		JMenuItem exitItem=new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		JMenuBar menuBar=new JMenuBar();
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				evaluate();
			}
		};
		expression=new JTextField(20);
		expression.addActionListener(listener);
		JButton evaluateButton=new JButton("Evaluate");
		evaluateButton.addActionListener(listener);
		
		typeCombo=new JComboBox(new Object[] {"STRING","NODE","NODESET","NUMBER","BOOLEAN"});
		typeCombo.setSelectedItem("STRING");
		
		JPanel panel=new JPanel();
		panel.add(expression);
		panel.add(typeCombo);
		panel.add(evaluateButton);
		docText=new JTextArea(10,40);
		result=new JTextField();
		result.setBorder(new TitledBorder("Result"));
		
		add(panel,BorderLayout.NORTH);
		add(new JScrollPane(docText),BorderLayout.CENTER);
		add(result, BorderLayout.SOUTH);
		
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
			builder=factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e);
		}
		XPathFactory xpfactory=XPathFactory.newInstance();
		path=xpfactory.newXPath();
		
		pack();
	}
	public void openFile() {
		JFileChooser chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "XML files";
			}
			
			@Override
			public boolean accept(File f) {
				// TODO Auto-generated method stub
				return f.isDirectory()||f.getName().toLowerCase().endsWith(".xml");
			}
		});
		int r=chooser.showOpenDialog(this);
		if(r!=JFileChooser.APPROVE_OPTION)return;
		File f=chooser.getSelectedFile();
		
		try {
			byte[] bytes=new byte[(int)f.length()];
			new FileInputStream(f).read(bytes);
			docText.setText(new String(bytes));
			doc=builder.parse(f);
			
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, e);
		}catch (SAXException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this,e);
		}
	}
	public void evaluate() {
		try {
			String typeName=(String) typeCombo.getSelectedItem();
			QName returnType=(QName) XPathConstants.class.getField(typeName).get(null);
			Object evalResult=path.evaluate(expression.getText(), doc, returnType);
			if(typeName.equals("NODESET")) {
				NodeList list=(NodeList) evalResult;
				StringBuilder builder=new StringBuilder();
				builder.append("{");
				for(int i=0;i<list.getLength();i++) {
					if(i>0) builder.append(",");
					builder.append(""+list.item(i));
				}
				builder.append("}");
				result.setText(""+builder);
			}else {
				result.setText(""+evalResult);
			}
		} catch (XPathExpressionException e) {
			// TODO: handle exception
			result.setText(""+e);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
public class XPathTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame=new XPathframe();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
