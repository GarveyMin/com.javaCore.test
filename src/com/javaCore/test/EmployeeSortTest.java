package com.javaCore.test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This program demonstrates the use of the Comparable interface
 * 
 * @author Garvey Min
 * @date 2018年6月4日下午4:36:19
 * @version 4.7.3a
 */

class EmployeeNum5 implements Comparable<EmployeeNum5> {

	private String name;
	private double salary;

	public EmployeeNum5(String n, double s) {
		name = n;
		salary = s;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	/**
	 * comparable employeeNum5 by salary
	 * 
	 * @param other
	 *            another EmployeeNum5 object
	 * @return a negative values if this employeeNum5 has a lower salary than
	 *         otherObject,0 if the salaries are the same,a positive values
	 *         otherwise
	 */
	@Override
	public int compareTo(EmployeeNum5 other) {
		// TODO Auto-generated method stub
		if (salary < other.salary)
			return -1;
		if (salary > other.salary)
			return 1;
		return 0;
	}
}

public class EmployeeSortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeNum5[] staff = new EmployeeNum5[3];

		staff[0] = new EmployeeNum5("Harry Hacker", 35000);
		staff[1] = new EmployeeNum5("Carl Cracker", 75000);
		staff[2] = new EmployeeNum5("Tony Tester", 38000);

		Arrays.sort(staff);
		// print out information about all employeeNum5 objects
		for (EmployeeNum5 e : staff) {
			System.out.println("name:" + e.getName() + ",salary:" + e.getSalary());

		}
	}

}
