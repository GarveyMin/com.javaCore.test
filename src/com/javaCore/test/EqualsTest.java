package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @date 2018年5月19日上午10:08:42
 * @version 4.7.3a
 */
class EmployeeNum3 {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum3(String n, double s, int year, int month, int day) {
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

	public boolean equals(Object otherObject) {
		// a quick test to see if the objects are identical
		if (this == otherObject)
			return true;
		// must return false if the explicit parameter is null
		if (this == null)
			return false;
		// if the classes don't match,they can't be equal
		if (getClass() != otherObject.getClass())
			return false;
		// now we know otherObject is a non-null EmployeeNum3
		EmployeeNum3 other = (EmployeeNum3) otherObject;
		// test whether the fields have identical values
		return name.equals(other.name) && salary == other.salary && hireDay == other.hireDay;
	}

	public int hashCode() {
		return 7 * name.hashCode() + 11 * new Double(salary).hashCode() + 13 * hireDay.hashCode();
	}

	public String toString() {
		return getClass().getName() + "[name:" + name + " ,salary:" + salary + " ,hireDay:" + hireDay;
	}
}

class ManagerNum extends EmployeeNum3 {

	private double bouns;

	public ManagerNum(String n, double s, int year, int month, int day) {
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

	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject))
			return false;
		ManagerNum other = (ManagerNum) otherObject;
		// super.equals checked that this and other belong to the same class
		return bouns == other.bouns;
	}

	public int hashCode() {
		return super.hashCode() + 17 * new Double(bouns).hashCode();
	}

	public String toString() {
		return super.toString() + "[bouns:" + bouns + "]";
	}
}

public class EqualsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeNum3 alice1 = new EmployeeNum3("Alice Adams", 75000, 1987, 12, 15);
		EmployeeNum3 alice2 = alice1;
		EmployeeNum3 alice3 = new EmployeeNum3("Alice Adams", 75000, 1987, 12, 15);
		EmployeeNum3 bob = new EmployeeNum3("Bob Brandson", 50000, 1989, 10, 1);

		System.out.println("alice1==alice2:" + (alice1 == alice2));
		System.out.println("alice1==alice3:" + (alice1 == alice3));
		System.out.println("alice1.equals(alice3):" + alice1.equals(alice3));
		System.out.println("alice1.equals(bob):" + alice1.equals(bob));
		System.out.println("bob.toString:" + bob);

		ManagerNum carl = new ManagerNum("Carl Cracker", 80000, 1987, 12, 15);
		ManagerNum boss = new ManagerNum("Carl Cracker", 80000, 1987, 12, 15);
		boss.setBouns(5000);
		System.out.println("boss.toString:" + boss);
		System.out.println("carl.equals(boss):" + carl.equals(boss));
		System.out.println("alice1.hashCode:" + alice1.hashCode());
		System.out.println("alice3.hashCode:" + alice3.hashCode());
		System.out.println("bob.hashCode:" + bob.hashCode());
		System.out.println("carl.hashCode:" + carl.hashCode());
	}

}
