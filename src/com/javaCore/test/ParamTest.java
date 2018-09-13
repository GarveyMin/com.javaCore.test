package com.javaCore.test;

/**
 * 
 * @author Garvey Min
 * @version 2018年4月23日 下午3:20:44
 */
class Employee {
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public String getName() {
		return this.name;
	}

	public double getSalary() {
		return this.salary;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
}

public class ParamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Test1:Methods can't modfiy numeric parameters
		 */
		System.out.println("Testing tripleValue:");
		double percent = 10;
		System.out.println("Before:percent" + percent);
		tripleValue(percent);
		System.out.println("After:percent" + percent);

		/*
		 * Test2:Methods can change the state of object parameters
		 */
		System.out.println("\nTesting tripleSalary:");
		Employee harry = new Employee("Harry", 50000);
		System.out.println("Before:salary" + harry.getSalary());
		tripleSalary(harry);
		System.out.println("After:salary" + harry.getSalary());

		/*
		 * Test3:Method can't attach new objects to object parameters
		 */
		System.out.println("\nTesting swap:");
		Employee a = new Employee("Alice", 70000);
		Employee b = new Employee("Bod", 60000);
		System.out.println("Before:a=" + a.getName());
		System.out.println("Before:b=" + b.getName());
		swap(a, b);
		System.out.println("After:a=" + a.getName());
		System.out.println("After:b=" + b.getName());
	}

	private static void swap(Employee x, Employee y) {
		// TODO Auto-generated method stub
		Employee temp = x;
		x = y;
		y = temp;
		System.out.println("End of method:x=" + x.getName());
		System.out.println("End of method:y=" + y.getName());
	}

	private static void tripleSalary(Employee x) {
		// TODO Auto-generated method stub
		x.raiseSalary(200);
		System.out.println("End of method:salary" + x.getSalary());
	}

	private static void tripleValue(double x) {
		// TODO Auto-generated method stub
		x = 3 * x;
		System.out.println("End of method:x=" + x);
	}

}
