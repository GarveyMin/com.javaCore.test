package com.javaCore.test;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * @author Garvey Min
 * @version 2018年5月16日 下午8:43:08
 */
abstract class Person {
	private String name;

	public Person(String n) {
		name = n;
	}

	public abstract String getDescription();

	public String getName() {
		return name;
	}
}

class EmpolyeeNum2 extends Person {
	private double salary;
	private Date hireDay;

	public EmpolyeeNum2(String n, double s, int year, int month, int day) {
		super(n);
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public String getDescription() {
		return String.format("an employee with a salary of $%.2f", salary);
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}

class Student extends Person {
	private String major;

	public Student(String n, String m) {
		// pass n to superclass constructor
		super(n);
		major = m;
	}

	public String getDescription() {
		return "a student majoring in" + major;
	}

}

public class PersonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fill the people array with Student and EmployeeNum2 objects
		Person[] people = new Person[2];

		people[0] = new EmpolyeeNum2("Harry Hacker", 50000, 1989, 10, 1);
		people[1] = new Student("Maria Morris", "computer science");
		// print out name and description of all Person objects
		for (Person p : people) {
			System.out.println(p.getName() + " " + p.getDescription());
		}
	}

}
