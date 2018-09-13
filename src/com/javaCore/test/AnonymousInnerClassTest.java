package com.javaCore.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;

/**
 * This program demonstrates anonymous inner class
 * 
 * @author Garvey Min
 * @date 2018年6月13日下午9:01:57
 * @version 4.7.3a
 */
// A clock that print the time in regular intervals
class TalkingClock1 {

	/**
	 * Start the clock
	 * 
	 * @param interval
	 *            the interval between(in millisecond)
	 * @param beep
	 *            true if the clock should beep
	 */

	public void start(int interval, final boolean beep) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date now = new Date();
				System.out.println("at the tone,the time is" + now);
				if (beep)
					Toolkit.getDefaultToolkit().beep();
			}
		};
		// Timer t=new Timer(interval,listener);
		// t.start;
	}
}

public class AnonymousInnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock1 clock = new TalkingClock1();
		clock.start(1000, true);

		JOptionPane.showMessageDialog(null, "Quit program");
		System.exit(0);
	}

}
