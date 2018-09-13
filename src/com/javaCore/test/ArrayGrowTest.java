package com.javaCore.test;

import java.lang.reflect.Array;

public class ArrayGrowTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 2, 3 };
		a = (int[]) goodArrayGrow(a);
		arrayPrint(a);

		String[] b = { "Tom", "Dick", "Harry" };
		b = (String[]) goodArrayGrow(b);
		arrayPrint(b);

		System.out.println("The follow call will generate an exception.");
		b = (String[]) badArrayGrow(b);
	}

	private static int[] goodArrayGrow(int[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method attempts to grow an array by allocating a new array and copy all
	 * elements.
	 * 
	 * @param a
	 *            the array to grow
	 * @return a larger array that contains all elements of a,However,the returned
	 *         array has type Object[],not the same type as a
	 */
	static Object[] badArrayGrow(Object[] a) {
		// TODO Auto-generated method stub
		int newLength = a.length * 11 / 10 + 10;
		Object[] newArray = new Object[newLength];
		System.arraycopy(a, 0, newArray, 0, a.length);
		return newArray;
	}

	private static void arrayPrint(String[] b) {
		// TODO Auto-generated method stub

	}

	private static String[] goodArrayGrow(String[] b) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * A convenience method to print all elements is an array
	 * 
	 * @param a
	 *            the array to print.It can be an object array or a primitive type
	 *            array
	 */
	static void arrayPrint(Object a) {
		// TODO Auto-generated method stub
		Class c1 = a.getClass();
		if (!c1.isArray())
			return;
		Class componentType = c1.getComponentType();
		int length = Array.getLength(a);
		System.out.print(componentType.getName() + "[" + length + "]" + "={");
		for (int i = 0; i < Array.getLength(a); i++) {
			System.out.print(Array.get(a, i) + " ");
			System.out.println("}");
		}
	}

	/**
	 * This method grow an array by allocating a new array of the same type and
	 * copying all elements.
	 * 
	 * @param a
	 *            the array to grow.This is can be an object array or primitive type
	 *            array
	 * @return a larger array that contain all elements of a
	 */
	static Object goodArrayGrow(Object[] a) {
		// TODO Auto-generated method stub
		Class c1 = a.getClass();
		if (!c1.isArray())
			return null;
		Class componentType = c1.getComponentType();
		int length = Array.getLength(a);
		int newLength = length * 11 / 10 + 10;

		Object newArray = Array.newInstance(componentType, newLength);
		System.arraycopy(a, 0, newArray, 0, length);
		return newArray;
	}

}
