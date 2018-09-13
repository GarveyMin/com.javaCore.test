package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @version 2018年5月16日 上午11:24:46
 */
class EmployeeNum1 {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum1(String n, double s, int year, int month, int day) {
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

class Manager extends EmployeeNum1 {
	private double bouns;

	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		bouns = 0;
	}

	public double getSalary() {
		double baseSalary = super.getSalary();
		return baseSalary + bouns;
	}

	public void setBouns(double b) {
		bouns = b;
	}
}

public class ManagerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// construct a Manager object
		Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBouns(5000);

		EmployeeNum1[] staff = new EmployeeNum1[3];
		// fill the staff array with Manager and EmpolyeeNum1 objects
		staff[0] = boss;
		staff[1] = new EmployeeNum1("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new EmployeeNum1("Tommy Tester", 40000, 1990, 3, 15);
		// print out information about all EmployeeNum1 objects
		for (EmployeeNum1 e : staff) {
			System.out.println("name:" + e.getName() + ", salary:" + e.getSalary());
		}

	}

}
