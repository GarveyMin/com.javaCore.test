package com.javaCore.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

import javax.jws.WebParam.Mode;

/**
 * 
 * @author Garvey Min
 * @date 2018年5月20日下午2:46:19
 * @version 4.7.3a
 */
public class ReflectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// read class name from command line args or user input
		String name;
		if (args.length > 0) {
			name = args[0];
		} else {
			Scanner in = new Scanner(System.in);
			System.out.println("Enter class name(e.g.java.util.Date):");
			name = in.next();
		}
		try {
			// print class name and superclass name(if !=Object)
			Class c1 = Class.forName(name);
			Class superc1 = c1.getSuperclass();
			String modifiers = Modifier.toString(c1.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
				System.out.print("Class" + name);
				if (superc1 != null && superc1 == Object.class) {
					System.out.print("extends" + superc1.getName());
				}
				System.out.print("\n{\n");
				printConstructors(c1);
				System.out.println();
				printMethods(c1);
				System.out.println();
				printFields(c1);
				System.out.println("}");
			}

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * prints all fields of a class
	 * 
	 * @param c1
	 *            a class
	 */

	public static void printFields(Class c1) {
		// TODO Auto-generated method stub
		Field[] field = c1.getDeclaredFields();

		for (Field f : field) {
			Class type = f.getType();
			String name = f.getName();
			System.out.print("");
			String modifiers = Modifier.toString(f.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
				System.out.println(type.getName() + "" + name + ";");
			}
		}
	}

	/**
	 * prints all method of a class
	 * 
	 * @param c1
	 *            a class
	 */

	public static void printMethods(Class c1) {
		// TODO Auto-generated method stub
		Method[] method = c1.getDeclaredMethods();

		for (Method m : method) {
			Class retType = m.getReturnType();
			String name = m.getName();
			System.out.print(" ");
			// print modifiers,return type,and method name
			String modifiers = Modifier.toString(m.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
				System.out.print(retType.getName() + " " + name + "(");
			}
			// print parameter types
			Class[] paramType = m.getParameterTypes();
			for (int j = 0; j < paramType.length; j++) {
				if (j > 0) {
					System.out.print(",");
					System.out.println(paramType[j].getName());
				}
				System.out.println(");");
			}
		}
	}

	/**
	 * prints all constructors of a class
	 * 
	 * @param c1
	 *            a class
	 */

	public static void printConstructors(Class c1) {
		// TODO Auto-generated method stub
		Constructor[] consrtuctor = c1.getDeclaredConstructors();

		for (Constructor c : consrtuctor) {
			String name = c.getName();
			System.out.print(" ");
			String modifiers = Modifier.toString(c.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
				System.out.print(name + "(");
			}
			// print parameter types
			Class[] paramTypes = c.getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0) {
					System.out.print(",");
					System.out.println(paramTypes[j].getName());
				}
				System.out.println(");");
			}
		}

	}

}
