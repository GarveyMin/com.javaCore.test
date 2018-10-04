package com.javaCore.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * a class whose clone method uses serialization
 */
class SerizalCloneable implements Cloneable, Serializable {

	public Object clone() {
		try {
			// save the object to a byte array
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();
			// read a clone of the object from the byte array
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			in.close();
			return ret;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}

	}
}

	/*
	 * The familiar Employee class, redefined to extend the SerialCloneable class.
	 */
	class EmployeeNum12 extends SerizalCloneable {
		private String name;
		private double salary;
		private Date hireDay;

		public EmployeeNum12() {

		}

		public EmployeeNum12(String n, double s, int year, int month, int day) {
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

		public String toString() {
			return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
		}

	}

public class SerizalCloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeNum12 harry=new EmployeeNum12("Harry Hacker", 35000, 1989, 10, 1);
		//clone harry
		EmployeeNum12 harry2=(EmployeeNum12) harry.clone();
		//mutate harry
		harry.raiseSalary(10);
		//now harry and clone are different
		System.out.println(harry);
		System.out.println(harry2);
		}
	}

