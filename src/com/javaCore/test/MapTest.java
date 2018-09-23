package com.javaCore.test;

import java.util.HashMap;
import java.util.Map;

class EmployeeNum8{
	private String name;
	private double salary;
	/**
	 * Constructs an employee with $0 salary.
	 * @param n the employee name
	 */
	public EmployeeNum8(String n) {
		name=n;
		salary=0;
	}
	public String toString() {
		return "[name="+name+",salary="+salary+"]";
	}
}
public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, EmployeeNum8> staff=new HashMap<String,EmployeeNum8>();
		staff.put("144-25-5464", new EmployeeNum8("Amy Lee"));
		staff.put("567-24-2546", new EmployeeNum8("Harry Hacker"));
		staff.put("157-62-7935", new EmployeeNum8("Gary Cooper"));
		staff.put("157-62-7935", new EmployeeNum8("Francesca Cruz"));
		// print all entries
		System.out.println(staff);
		// remove an entry
		staff.remove("567-24-2546");
		// replace an entry
		staff.put("456-62-5527", new EmployeeNum8("Francesca Miller"));
		// look up a value
		System.out.println(staff.get("157-62-7935"));
		// iterate through all entries
		for(Map.Entry<String, EmployeeNum8> entry:staff.entrySet()) {
			String key=entry.getKey();
			EmployeeNum8 value=entry.getValue();
			System.out.println("key="+key+",value="+value);
		}
	}

}
