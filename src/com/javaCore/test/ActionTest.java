package com.javaCore.test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 * 
 * @author Garvey Min
 * @date 2018年7月2日上午10:47:31
 * @version 4.7.3a
 */
/*
 * A frame with a panel that demonstrates color change actions
 */
class ActionFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	private JPanel buttonPanel;

	public ActionFrame() {
		setTitle("Action Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		buttonPanel = new JPanel();
		// define actions
		Action yellowAction = new ColorAction("Yellow", Color.YELLOW);
		Action blueAction = new ColorAction("Blue", Color.BLUE);
		Action redAction = new ColorAction("Red", Color.RED);
		// add buttons for these actions
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));
		// add panel to frame
		add(buttonPanel);
		// associate the Y,B and R keys with names
		InputMap imap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		imap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		imap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		imap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");
		// associate the names with actions
		ActionMap amap = buttonPanel.getActionMap();
		amap.put("panel.yellow", yellowAction);
		amap.put("panel.blue", blueAction);
		amap.put("panel.red", redAction);
	}

	public class ColorAction extends AbstractAction {
		/**
		 * Constructs a color actions
		 * 
		 * @param name
		 *            the name show on the button
		 * @param c
		 *            the background color
		 */
		public ColorAction(String name, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to" + name.toLowerCase());
			putValue("color", c);
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}

	}
}

public class ActionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				ActionFrame frame = new ActionFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
