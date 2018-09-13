package com.javaCore.test;

import java.util.Random;

/**
 * This program demonstrates the use of static inner class
 * 
 * @author Garvey Min
 * @date 2018年6月13日下午9:38:23
 * @version 4.7.3a
 */
class ArrayAlg {
	// A pair of floating-point numbers
	public static class Pair {

		private double first;
		private double second;

		/**
		 * Constructs a pair form two floating-point numbers
		 * 
		 * @param f
		 *            the first number
		 * @param s
		 *            the second number
		 */
		public Pair(double f, double s) {
			first = f;
			second = s;
		}

		/**
		 * Return the first number of the pair
		 * 
		 * @return the first number
		 */
		public double getFirst() {
			return first;
		}

		/**
		 * Return the second number of the pair
		 * 
		 * @return the second number
		 */
		public double getSecond() {
			return second;
		}
	}

	/**
	 * Compute both the minimum and maximum of an array
	 * 
	 * @param values
	 *            an array of floating-point number
	 * @return a pair whose first element is the minimum and whose second element is
	 *         the maximum
	 */
	public static Pair minmax(double[] values) {
		double min = Double.MIN_VALUE;
		double max = Double.MAX_VALUE;
		for (double v : values) {
			if (min > v)
				min = v;
			if (max < v)
				max = v;
		}

		return new Pair(min, max);

	}
}

public class StaticInnerClassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[] d = new double[20];
		for (int i = 0; i < d.length; i++) {
			d[i] = 100 * Math.random();
			ArrayAlg.Pair p = ArrayAlg.minmax(d);
			System.out.println("min=" + p.getFirst());
			System.out.println("max=" + p.getSecond());
		}
	}

}
