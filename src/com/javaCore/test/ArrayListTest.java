package com.javaCore.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @date 2018年5月19日下午8:57:36
 * @version 4.7.3a
 */
class EmployeeNum4 {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum4(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
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

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fill the staff array list with three EmployeeNum4 objects
		ArrayList<EmployeeNum4> staff = new ArrayList<EmployeeNum4>();
		staff.add(new EmployeeNum4("Carl Cracker", 75000, 1987, 12, 15));
		staff.add(new EmployeeNum4("Harry Hacker", 50000, 1989, 10, 1));
		staff.add(new EmployeeNum4("Tony Tester", 40000, 1990, 3, 15));

		// raise everyone's salary by 5%
		for (EmployeeNum4 e : staff) {
			e.raiseSalary(5);
		}
		// print out information about all EmployeeNum4 objects
		for (EmployeeNum4 e : staff) {
			System.out.println("name:" + e.getName() + " ,salary:" + e.getSalary() + ", hireDay:" + e.getHireDay());
		}
	}

}
