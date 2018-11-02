package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

class XMLWriteFrame extends JFrame{
	
	public static final int DEFAULT_WIDTH=300;
	public static final int DEFAULT_HEIGHT=200;
	
	private RectangleComponent comp;
	private JFileChooser chooser;
	
	public XMLWriteFrame() {
		setTitle("XMLWrite");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		chooser=new JFileChooser();
		comp=new RectangleComponent();
		add(comp);
		
		JMenuBar menuBar=new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu=new JMenu("File");
		menuBar.add(menu);
	
		JMenuItem newItem=new JMenuItem("New");
		menu.add(newItem);
		newItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				comp.newDrawing();
			}
		});
		
		JMenuItem saveItem=new JMenuItem("Save");
		menu.add(saveItem);
		saveItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					saveDocument();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(XMLWriteFrame.this,e.toString());
				}
			}
		});
		
		JMenuItem saveAXItem=new JMenuItem("Save with stAX");
		menu.add(saveAXItem);
		saveAXItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					saveStAx();
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(XMLWriteFrame.this,e.toString());
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
	}
	public void saveDocument() throws TransformerException,IOException{
		if(chooser.showSaveDialog(this)!=JFileChooser.APPROVE_OPTION) return;
		File f=chooser.getSelectedFile();
		Document doc=comp.buildeDocument();
		Transformer t=TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, 
				"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd");
		t.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "-//W3C//DTD SVG 20000802//EN");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.setOutputProperty(OutputKeys.METHOD, "xml");
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(f)));
	}
	public void saveStAx() throws FileNotFoundException,XMLStreamException{
		if(chooser.showSaveDialog(this)!=JFileChooser.APPROVE_OPTION) return;
		File f=chooser.getSelectedFile();
		XMLOutputFactory factory=XMLOutputFactory.newInstance();
		XMLStreamWriter writer=factory.createXMLStreamWriter(new FileOutputStream(f));
		comp.writeDocument(writer);
		writer.close();
	}
}
class RectangleComponent extends JComponent{
	
	private ArrayList<Rectangle2D> rects;
	private ArrayList<Color> colors;
	private Random generator;
	private DocumentBuilder builder;
	
	public RectangleComponent() {
		rects=new ArrayList<Rectangle2D>();
		colors=new ArrayList<Color>();
		generator=new Random();
		
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		try {
			builder=factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void newDrawing() {
		int n=10+generator.nextInt(20);
		rects.clear();
		colors.clear();
		for(int i=0;i<=n;i++) {
			int x=generator.nextInt(getWidth());
			int y=generator.nextInt(getHeight());
			int width=generator.nextInt(getWidth()-x);
			int height=generator.nextInt(getHeight()-y);
			rects.add(new Rectangle(x,y,width,height));
			int r=generator.nextInt(256);
			int g=generator.nextInt(256);
			int b=generator.nextInt(256);
			colors.add(new Color(r, g, b));
		}
		repaint();
	}
	public void paintComponent(Graphics g) {
		if(rects.size()==0) newDrawing();
		Graphics2D g2=(Graphics2D) g;
		
		for(int i=0;i<rects.size();i++) {
			g2.setPaint(colors.get(i));
			g2.fill(rects.get(i));
		}
	}
	public Document buildeDocument() {
		Document doc=builder.newDocument();
		Element svgElement=doc.createElement("svg");
		doc.appendChild(svgElement);
		svgElement.setAttribute("width", ""+getWidth());
		svgElement.setAttribute("height", ""+getHeight());
		for(int i=0;i<rects.size();i++) {
			Color c=colors.get(i);
			Rectangle2D r=rects.get(i);
			Element rectElement=doc.createElement("rect");
			rectElement.setAttribute("x", ""+r.getX());
			rectElement.setAttribute("y", ""+r.getY());
			rectElement.setAttribute("width", ""+r.getWidth());
			rectElement.setAttribute("height", ""+r.getHeight());
			rectElement.setAttribute("fill", colorToString(c));
		}
		return doc;	
	}
	public void writeDocument(XMLStreamWriter write) throws XMLStreamException {
		write.writeStartDocument();
		write.writeDTD("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20000802//EN\" " 
	            + "\"http://www.w3.org/TR/2000/CR-SVG-20000802/DTD/svg-20000802.dtd\">");
		write.writeStartElement("svg");
		write.writeAttribute("width", ""+getWidth());
		write.writeAttribute("height", ""+getHeight());
		for(int i=0;i<=rects.size();i++) {
			Color c=colors.get(i);
			Rectangle2D r=rects.get(i);
			write.writeEmptyElement("rect");
			write.writeAttribute("x", ""+r.getX());
			write.writeAttribute("y", ""+r.getY());
			write.writeAttribute("width", ""+getWidth());
			write.writeAttribute("height", ""+getHeight());
			write.writeAttribute("fill", colorToString(c));
		}
		write.writeEndDocument();
	}
	private static String colorToString(Color c) {
		StringBuffer buffer=new StringBuffer();
		buffer.append(Integer.toString(c.getRGB() & 0xFFFFFF));
		while(buffer.length()<6)
			buffer.insert(0, '0');
		buffer.insert(0, '#');
		return buffer.toString();
	}
}
public class XMLWriteTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				XMLWriteFrame frame=new XMLWriteFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
