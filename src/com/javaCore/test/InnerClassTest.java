package com.javaCore.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;

/**
 * 这个程序缺少Timer构造器，因为在JDK8.0中Timer没有 Timer(int interval, AvtionListener listener)
 * 方法，所以无法实现此程序
 * 
 * @author Garvey Min
 * @date 2018年6月9日下午10:46:04
 * @version 4.7.3a
 */

class TalkingClock {
	private int interval;
	private boolean beep;

	/**
	 * Constructs a talking clock
	 * 
	 * @param interval
	 *            the interval message(in millisecond)
	 * @param beep
	 *            true if the clock should beep
	 */
	public TalkingClock(int interval, boolean beep) {
		// super();
		this.interval = interval;
		this.beep = beep;
	}

	/**
	 * Starts the clock
	 */
	public void start() {
		ActionListener listener = new TimerPrint01();
		// Timer t=new Timer(10000,listener);
		// t.start();

	}

	class TimerPrint01 implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			Date now = new Date();
			System.out.println("At the tone,the time is" + now);
			if (beep)
				Toolkit.getDefaultToolkit().beep();
		}
	}
}

public class InnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TalkingClock clock = new TalkingClock(1000, true);
		clock.start();
		// keep program running until user selects "OK"
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}
