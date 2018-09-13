package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @version 2018年4月23日 下午1:43:34
 */
class Employees {
	private String name;
	private double salary;
	private Date hireday;

	public Employees(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);

		// GregorianCalendar use 0 for January
		hireday = calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireday() {
		return hireday;
	}

	public void raiseSalary(double byPecent) {
		double raise = salary * byPecent;
		salary += raise;
	}
}

public class EmployeeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fill the staff array with three Employee Objects
		Employees[] staff = new Employees[3];
		staff[0] = new Employees("Carl Craker", 75000, 1987, 12, 15);
		staff[1] = new Employees("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employees("Tony Tester", 40000, 1990, 3, 5);

		// raise everyone's salary by 5%
		for (Employees e : staff) {
			e.raiseSalary(5);
		}

		// print out information about all Employee objects
		for (Employees e : staff) {
			System.out.println("name:" + e.getName() + ",salary=" + e.getSalary() + ",hireday=" + e.getHireday());
		}
	}

}
