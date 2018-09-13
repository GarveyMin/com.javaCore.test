package com.javaCore.test;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CalendarTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		System.out.println("April");
		// ����������������
		// construct d as current date
		GregorianCalendar d = new GregorianCalendar();
		// ����get������ȡ��ǰ���º���
		int today = d.get(Calendar.DAY_OF_MONTH);
		int month = d.get(Calendar.MONTH);

		// ��d����Ϊ����µĵ�һ�죬���õ���һ�������ڼ�
		// set d to start date of the month
		d.set(Calendar.DAY_OF_MONTH, 1);
		int weekday = d.get(Calendar.DAY_OF_WEEK);

		// ����getFirstDayWeek������õ�ǰ�������ڵ���ʼ��
		// get first day of week
		int firstDayOfWeek = d.getFirstDayOfWeek();

		// datermine the required indentation for the first line
		int indent = 0;
		while (weekday != firstDayOfWeek) {
			indent++;
			d.add(Calendar.DAY_OF_MONTH, -1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		}
		// �����ʾ���ڵ�ͷ������DateFormatSymbol��ʵ��,����getShortWeekdays��ȡ��ʾ���ڼ��Ķ�����
		// print weekday names
		String[] weekdayName = new DateFormatSymbols().getShortWeekdays();
		do {
			System.out.printf("%4s", weekdayName[weekday]);
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);
		} while (weekday != firstDayOfWeek);
		System.out.println();

		for (int i = 1; i <= indent; i++) {
			System.out.print(" ");
		}
		d.set(Calendar.DAY_OF_MONTH, 1);
		do {
			// print day
			int day = d.get(Calendar.DAY_OF_MONTH);
			System.out.printf("%3d", day);
			if (day == today) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}
			// advanced d to the next day
			d.add(Calendar.DAY_OF_MONTH, 1);
			weekday = d.get(Calendar.DAY_OF_WEEK);

			// start new line at the start of the week
			if (weekday == firstDayOfWeek) {
				System.out.println();
			}
		} while (d.get(Calendar.MONTH) == month);
		// the loop exits when d is day 1 of the next month

		// print final end of line if necessary
		if (weekday != firstDayOfWeek) {
			System.out.println();
		}
	}

}
