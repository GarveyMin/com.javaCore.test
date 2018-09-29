package com.javaCore.test;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * This frame has two buttons to fill a combo box from a separate thread. The "Good" button uses the
 * event queue, the "Bad" button modifies the combo box directly.
 */
class SwingThreadFrame extends JFrame {

	public SwingThreadFrame() {
		setTitle("SwingThreadTest");
		final JComboBox combo = new JComboBox();
		combo.insertItemAt(Integer.MAX_VALUE, 0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);

		JPanel panel = new JPanel();
		JButton goodButton = new JButton("Good");
		goodButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				new Thread(new GoodWorkerRunnable(combo)).start();
				;
			}
		});
		panel.add(goodButton);
		JButton BadButton = new JButton("Bad");
		BadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				new Thread(new BadWorkerRunnable(combo)).start();
			}
		});
		panel.add(BadButton);
		panel.add(combo);
		add(panel);
		pack();
	}
}

/*
 * This runnable modifies a combo box by randomly adding and removing numbers.
 * This can result in errors because the combo box methods are not synchronized
 * and both the worker thread and the event dispatch thread access the combo
 * box.
 */
class BadWorkerRunnable implements Runnable {
	private JComboBox combo;
	private Random generator;

	public BadWorkerRunnable(JComboBox aCombo) {
		// TODO Auto-generated constructor stub
		combo = aCombo;
		generator = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				int i = Math.abs(generator.nextInt());
				if (i % 2 == 0)
					combo.insertItemAt(i, 0);
				else if (combo.getItemCount() > 0)
					combo.removeItemAt(i % combo.getItemCount());
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

/*
 * This runnable modifies a combo box by randomly adding and removing numbers.
 * In order to ensure that the combo box is not corrupted, the editing
 * operations are forwarded to the event dispatch thread.
 */
class GoodWorkerRunnable implements Runnable {
	private JComboBox combo;
	private Random generator;

	public GoodWorkerRunnable(JComboBox aCombo) {
		// TODO Auto-generated constructor stub
		combo = aCombo;
		generator = new Random();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int i = Math.abs(generator.nextInt());
						if (i % 2 == 0)
							combo.insertItemAt(i, 0);
						else if (combo.getItemCount() > 0)
							combo.removeItemAt(i % combo.getItemCount());
					}
				});
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

public class SwingThreadTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SwingThreadFrame frame = new SwingThreadFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
