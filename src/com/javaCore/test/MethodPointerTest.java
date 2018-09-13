package com.javaCore.test;

import java.lang.reflect.Method;

/**
 * 
 * @author Garvey Min
 * @date 2018年6月4日上午10:17:44
 * @version 4.7.3a
 */
public class MethodPointerTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// get method pointer to the square and sqrt method
		Method square = MethodPointerTest.class.getMethod("square", double.class);
		Method sqrt = Math.class.getMethod("sqrt", double.class);

		// print table of x-and y-values
		printTable(1, 10, 10, square);
		printTable(1, 10, 10, sqrt);
	}

	/**
	 * returns the square of a number
	 * 
	 * @param x
	 *            a number
	 * @return x square
	 */
	public static double square(double x) {
		return x * x;
	}

	/**
	 * prints a table with x-and y-vales for a method
	 * 
	 * @param from
	 *            the lower bound for the x-values
	 * @param to
	 *            the upper bound for the x-values
	 * @param n
	 *            the number of rows in the table
	 * @param f
	 *            a method with a double parameter and double return value
	 */
	public static void printTable(double from, double to, int n, Method f) {
		// TODO Auto-generated method stub
		// print out the method as table header
		System.out.println(f);
		double dx = (to - from) / (n - 1);
		for (double x = from; x <= to; x += dx) {
			try {
				double y = (Double) f.invoke(null, x);
				System.out.printf("%10.4f|%10.4f%n", x, y);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

}
