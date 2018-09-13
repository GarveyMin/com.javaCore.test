package com.javaCore.test;

import java.util.Random;

/**
 * 
 * @author Garvey Min
 * @version 2018年5月6日 下午4:18:59
 */
class Employeed {
	private int id;
	private String name = ""; // instance field initialization
	private double salary;
	private static int nextId;
	// static initialization block
	static {
		Random generator = new Random();
		// set nextId to a random number between o and 9999
		nextId = generator.nextInt(10000);
	}
	// object initialization block
	{
		id = nextId;
		nextId++;
	}

	// three overloaded constructors
	public Employeed(String n, double s) {
		name = n;
		salary = s;
	}

	public Employeed(double s) {
		// calls the Employee(String double)constructor
		this("Employee #" + nextId, s);
	}

	// the default constructor
	public Employeed() {
		// 默认的构造器，即无参的构造器
		// name initialized to " "--see below
		// salary initialized set--initialized to 0
		// id initialized in initialization block
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
}

public class ConstructorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// fill the staff array with the three Employee objects
		Employeed[] staff = new Employeed[3];

		staff[0] = new Employeed("Harry", 40000);
		staff[1] = new Employeed(60000);
		staff[2] = new Employeed();

		for (Employeed e : staff) {
			System.out.println("name:" + e.getName() + ",id:" + e.getId() + ",salary:" + e.getSalary());
		}
	}

}
