package com.javaCore.test;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class ImageFrames extends JFrame{
	public static final int DEFAULT_WIDTH=450;
	public static final int DEFAULT_HEIGHT=350;
	/**
	 * 
	 * @param image the image to display
	 */
	public ImageFrames(Image image) {
		setTitle("capture");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JLabel label=new JLabel(new ImageIcon(image));
		add(label);
	}
}

public class RobotTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				ButtonFrames frame=new ButtonFrames();
				
				//make frame with a button panel
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				
				//attach a robot to the screen device
				GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
				GraphicsDevice screen=environment.getDefaultScreenDevice();
				
				try {
					Robot robot=new Robot(screen);
					runTest(robot);
					
				} catch (AWTException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Runs a simple test procedure
	 * @param robot
	 */
	public static void runTest(Robot robot) {
		//simulate a space bar press
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		// simulate a tab key followed by a space
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyPress(' ');
		robot.keyRelease(' ');
		
		// simulate a mouse click over the rightmost button
		robot.delay(2000);
		robot.mouseMove(200, 50);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		
		// capture the screen and show the resulting image
		robot.delay(2000);
		BufferedImage image=robot.createScreenCapture(new Rectangle(0,0,400,300));
		
		ImageFrames frame=new ImageFrames(image);
		frame.setVisible(true);
	}

}
