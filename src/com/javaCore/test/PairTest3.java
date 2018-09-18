package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

class PairAlg {
	public static boolean hasNulls(Pair<?> p) {

		return p.getFirst() == null || p.getSecond() == null;
	}

	public static void swap(Pair<?> p) {
		swapHelper(p);
	}

	public static <T> void swapHelper(Pair<T> p) {
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(t);
	}
}

class EmployeeNum7 {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum7(String n, double s, int years, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(years, month - 1, day);
		hireDay = calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}

class Manager1 extends EmployeeNum7 {
	private double bouns;

	public Manager1(String n, double s, int years, int month, int day) {
		super(n, s, years, month, day);
		bouns = 0;
	}

	public double getSalay() {
		double baseSalary = super.getSalary();
		return baseSalary + bouns;
	}

	public void setBouns(double b) {
		bouns = b;
	}

	public double getBouns() {
		return bouns;
	}
}

public class PairTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager1 ceo = new Manager1("Gus Greed", 800000, 2003, 12, 15);
		Manager1 cfo = new Manager1("Sid Sneaky", 600000, 2003, 12, 15);
		Pair<Manager1> buddies = new Pair<Manager1>(ceo, cfo);
		printBuddies(buddies);

		ceo.setBouns(1000000);
		cfo.setBouns(500000);
		Manager1[] managers = { ceo, cfo };

		Pair<EmployeeNum7> result = new Pair<EmployeeNum7>();
		minmaxBouns(managers, result);
		System.out.println("first:" + result.getFirst().getName() + ", second:" + result.getSecond().getName());
		maxminBouns(managers, result);
		System.out.println("first:" + result.getFirst().getName() + ", second:" + result.getSecond().getName());
	}

	public static void printBuddies(Pair<? extends EmployeeNum7> p) {
		EmployeeNum7 first = p.getFirst();
		EmployeeNum7 second = p.getSecond();
		System.out.println(first.getName() + " and " + second.getName() + " are buddies.");
	}

	public static void minmaxBouns(Manager1[] a, Pair<? super Manager1> result) {
		if (a == null || a.length == 0)
			return;
		Manager1 min = a[0];
		Manager1 max = a[0];
		for (int i = 1; i < a.length; i++) {
			if (min.getBouns() > a[i].getBouns()) {
				min = a[i];
			}
			if (max.getBouns() < a[i].getBouns()) {
				max = a[i];
			}
			result.setFirst(min);
			result.setSecond(max);
		}
	}

	public static void maxminBouns(Manager1[] a, Pair<? super Manager1> result) {
		minmaxBouns(a, result);
		PairAlg.swapHelper(result);// OK--swapHelper captures wildcard type
	}

}
