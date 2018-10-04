package com.javaCore.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

class EmployeeNum11 implements Serializable {
	private String name;
	private double salary;
	private Date hireDay;

	public EmployeeNum11() {

	}

	public EmployeeNum11(String n, double s, int year, int month, int day) {
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

class ManagerNum1 extends EmployeeNum11 {

	private EmployeeNum11 secretary;

	/**
	 * Constructs a Manager without a secretary
	 * 
	 * @param n the employee's name
	 * @param s the salary
	 * @param y the hire year
	 * @param m the hire month
	 * @param d the hire day
	 */
	public ManagerNum1(String n, double s, int y, int m, int d) {
		super(n, s, y, m, d);
		secretary = null;
	}

	/**
	 * Assigns a secretary to the manager.
	 * 
	 * @param s the secretary
	 */
	public void setSecretary(EmployeeNum11 s) {
		secretary = s;
	}

	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}
}

public class ObjectStreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeNum11 harry = new EmployeeNum11("Harry Hacker", 50000, 1989, 10, 1);
		ManagerNum1 carl = new ManagerNum1("Carl Cracker", 80000, 1987, 12, 15);
		carl.setSecretary(harry);
		ManagerNum1 tony = new ManagerNum1("Tony Tester", 40000, 1990, 3, 15);
		tony.setSecretary(harry);
		EmployeeNum11[] staff = new EmployeeNum11[3];
		staff[0] = carl;
		staff[1] = harry;
		staff[2] = tony;
		try {
			// save all employee records to the file employeeNum1.dat
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employeeNum1.dat"));
			out.writeObject(staff);
			out.close();
			// retrieve all records into a new array
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("employeeNum1.dat"));
			EmployeeNum11[] newStaff = (EmployeeNum11[]) in.readObject();
			in.close();
			// raise secretary's salary
			newStaff[1].raiseSalary(10);
			// print the newly read employee records
			for (EmployeeNum11 e : newStaff) {
				System.out.println(e);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
