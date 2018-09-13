package com.javaCore.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;
import javax.tools.Tool;

/**
 * 这个程序缺少Timer构造器，因为在JDK8.0中Timer没有 Timer(int interval, AvtionListener listener)
 * 方法，所以无法实现时间
 * 
 * @author Garvey Min
 * @date 2018年6月8日下午10:31:31
 * @version 4.7.3a
 */
class TimerPrinter implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		Date now = new Date();
		System.out.println("At the tone,the time is" + now);
		Toolkit.getDefaultToolkit().beep();
	}

}

public class TimerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ActionListener listener = new TimerPrinter();
		// Timer t=new Timer(10000,listener);
		// t.start();
		JOptionPane.showMessageDialog(null, "Quit program?");
		System.exit(0);
	}

}
