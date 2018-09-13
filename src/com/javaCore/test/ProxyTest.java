package com.javaCore.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

class TraceHandler implements InvocationHandler {

	public TraceHandler(Object t) {
		// TODO Auto-generated constructor stub
		target = t;
	}

	@Override
	public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.print(target);
		System.out.print("." + m.getName() + "(");
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				System.out.print(args[i]);
				if (i < args.length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println(")");
		}
		return m.invoke(target, args);
	}

	private Object target;
}

public class ProxyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Object[] element = new Object[1000];

		for (int i = 0; i < element.length; i++) {
			Integer value = i + 1;
			InvocationHandler handler = new TraceHandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, handler);
			element[i] = proxy;
		}
		Integer key = new Random().nextInt(element.length) + 1;
		int result = Arrays.binarySearch(element, key);
		if (result >= 0)
			System.out.println(element[result]);
	}

}
