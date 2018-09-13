package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.SystemColor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月23日下午1:30:13
 * @version 4.7.3a
 */

/**
 * A frame that contains a panel with drawing
 */
class DrawFrame extends JFrame {

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;

	public DrawFrame() {
		setTitle("DrawTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// add panel to frame
		DrawComponent component = new DrawComponent();
		add(component);
	}
}

/*
 * A component that displays rectangles and ellipses
 */
class DrawComponent extends JComponent {

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		// draw a rectangle
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
		g2.draw(rect);

		// draw the enclosed ellipse
		Ellipse2D ellipse = new Ellipse2D.Double();
		ellipse.setFrame(rect);
		g2.draw(ellipse);

		// draw a diagonal line
		g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

		// draw a circle with same center
		double centerX = rect.getCenterX();
		double centerY = rect.getCenterY();
		double radius = 150;

		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
		g2.draw(circle);
		g2.setPaint(new Color(0, 128, 128));
		g2.fill(circle);
	}
}

public class DrawTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DrawFrame frame = new DrawFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
