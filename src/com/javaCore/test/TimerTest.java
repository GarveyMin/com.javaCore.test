package com.javaCore.test;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;

import javax.swing.JOptionPane;
import javax.tools.Tool;

/**
 * �������ȱ��Timer����������Ϊ��JDK8.0��Timerû�� Timer(int interval, AvtionListener listener)
 * �����������޷�ʵ��ʱ��
 * 
 * @author Garvey Min
 * @date 2018��6��8������10:31:31
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
