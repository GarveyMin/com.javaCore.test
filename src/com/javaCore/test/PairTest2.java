package com.javaCore.test;

import java.util.Calendar;
import java.util.GregorianCalendar;

class ArrayAlg2{
	/**
	 * Gets the minimum and maximum of an array of objects of type T.
	 * @param a an array of objects of type T
	 * @return a pair with the min and max value, or null if a is 
      null or empty
	 */
	public static<T extends Comparable> Pair<T> minmax(T[] a){
		if(a==null||a.length==0) return null;
		T min=a[0];
		T max=a[0];
		for(int i=1;i<a.length;i++) {
			
			if(min.compareTo(a[1])>0) 
				min=a[i];
			if(max.compareTo(a[i])<0)
				max=a[i];
		}
		return new Pair<T>(min, max);
	}
}
public class PairTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GregorianCalendar[] birthday= {
				new GregorianCalendar(1973,Calendar.JANUARY,17),
				new GregorianCalendar(1973,Calendar.DECEMBER,9),
				new GregorianCalendar(1997,Calendar.AUGUST,3),
				new GregorianCalendar(2004,Calendar.DECEMBER,19)
		};
		Pair<GregorianCalendar> mm=ArrayAlg2.minmax(birthday);
		System.out.println("min="+mm.getFirst().getTime());
		System.out.println("max="+mm.getSecond().getTime());
	}

}
