package com.javaCore.test;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
class AnimationFrame extends JFrame{
	
	private static final int DEFAULT_WIDTH=300;
	private static final int DEFAULT_HEIGHT=300;
	
	public AnimationFrame() {
		ArrayComponent comp=new ArrayComponent();
		add(comp, BorderLayout.CENTER);
		final Sorter sorter=new Sorter(comp);
		
		JButton runBotton=new JButton("Run");
		runBotton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				sorter.setRun();
			}
		});
		JButton stepButton=new JButton("Step");
		stepButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				sorter.setStep();
			}
		});
		JPanel buttons=new JPanel();
		buttons.add(runBotton);
		buttons.add(stepButton);
		add(buttons,BorderLayout.NORTH);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Thread t=new Thread(sorter);
		t.start();
	}
}
class Sorter implements Runnable{
	
	private Double[] values;
	private ArrayComponent component;
	private Semaphore gate;
	private static final int DELAY=100;
	private volatile boolean run;
	private static final int VALUES_LENGTH=30;
	
	public Sorter(ArrayComponent comp) {
		// TODO Auto-generated constructor stub
		values=new Double[VALUES_LENGTH];
		for(int i=0;i<values.length;i++)
			values[i]=new Double(Math.random());
		this.component=comp;
		this.gate=new Semaphore(1);
		this.run=false;
		
	}
	public void setRun() {
		run=true;
		gate.release();
	}
	public void setStep() {
		run=false;
		gate.release();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Comparator<Double> comp=new Comparator<Double>() {

			@Override
			public int compare(Double i1, Double i2) {
				// TODO Auto-generated method stub
				component.setValues(values, i1, i2);
				try {
					if(run)Thread.sleep(DELAY);
					else gate.acquire();
				} catch (InterruptedException e) {
					// TODO: handle exception
					Thread.currentThread().interrupt();
				}
				return i1.compareTo(i2);
			}
		};
		Arrays.sort(values, comp);
		component.setValues(values, null, null);
	}
	
}
class ArrayComponent extends JComponent{
	private Double marked1;
	private Double marked2;
	private Double[] values;
	
	public ArrayComponent() {
		//empty
	}
	
	public synchronized void setValues(Double[] values,Double marked1,Double marked2) {
		this.values=values.clone();
		this.marked1=marked1;
		this.marked2=marked2;
		repaint();		
	}
	public synchronized void paintComponent(Graphics g) {
		if(values==null) return;
		Graphics2D g2=(Graphics2D) g;
		int width=getWidth()/values.length;
		for(int i=0;i<values.length;i++) {
			double height=values[i]*getHeight();
			Rectangle2D bar=new Rectangle2D.Double(width*1,0,width,height);
			if(values[i]==marked1||values[1]==marked2) g2.fill(bar);
			else g2.draw(bar);
		}
	}
}
public class AlgorithmAnimation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				AnimationFrame frame=new AnimationFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
