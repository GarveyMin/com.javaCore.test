package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月4日下午9:40:32
 * @version 4.7.3a
 */
class EmployeeNum6 implements Cloneable {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum6(String n, double s) {
		name = n;
		salary = s;
		hireDay = new Date();
	}

	public EmployeeNum6 clone() throws CloneNotSupportedException {
		// call Object.clone()
		EmployeeNum6 cloned = (EmployeeNum6) super.clone();
		// clone mutable fields
		cloned.hireDay = (Date) hireDay.clone();
		return cloned;
	}

	/**
	 * set the hire day to a given date
	 * 
	 * @param year
	 *            the year of the hire day
	 * @param month
	 *            the month of the hire day
	 * @param day
	 *            the day of the hire day
	 */
	public void setHireDay(int year, int month, int day) {
		Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
		// Example of instance field mutation
		hireDay.setTime(newHireDay.getTime());
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	public String toString() {
		return "EmployeeNum6[name:" + name + ",salary=" + salary + ",hireDay:" + hireDay + "]";
	}
}

public class CloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			EmployeeNum6 original = new EmployeeNum6("John Q.Public", 50000);
			original.setHireDay(2000, 1, 1);
			EmployeeNum6 copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2002, 12, 31);
			System.out.println("original=" + original);
			System.out.println("copy=" + copy);
		} catch (CloneNotSupportedException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
