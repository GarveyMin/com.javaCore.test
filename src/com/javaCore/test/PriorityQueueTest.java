package com.javaCore.test;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<GregorianCalendar> pq=new PriorityQueue<GregorianCalendar>();
		pq.add(new GregorianCalendar(1972,GregorianCalendar.DECEMBER,9));
		pq.add(new GregorianCalendar(1973,GregorianCalendar.JANUARY,17));
		pq.add(new GregorianCalendar(1997,GregorianCalendar.OCTOBER,3));
		pq.add(new GregorianCalendar(2004,GregorianCalendar.DECEMBER,19));
		System.out.println("Iterating over elements...");
		
		for(GregorianCalendar date:pq)
			System.out.println(date.get(Calendar.YEAR));
		System.out.println("Removing elements...");
		while(!pq.isEmpty())
			System.out.println(pq.remove().get(Calendar.YEAR));
	}

}
