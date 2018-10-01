package com.javaCore.test;


import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

class EmployeeNum9{
	private String name;
	private double salary;
	private Date hireDay;
	
	public EmployeeNum9() {
		
	}
	public EmployeeNum9(String n,double s,int year,int month,int day) {
		name=n;
		salary=s;
		GregorianCalendar calender=new GregorianCalendar(year,month-1,day);
		hireDay=calender.getTime();
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
		double raise=salary*byPercent/100;
		salary+=raise;
	}
	public String toString() {
		return getClass().getName()+"[name="+name+",salary="+salary+"hireDay="+hireDay+"]";
	}
	public void writeData(PrintWriter out) {
		GregorianCalendar calendar=new GregorianCalendar();
		calendar.setTime(hireDay);
		out.println(name+"|"+salary+"|"+calendar.get(Calendar.YEAR)+"|"+(calendar.get(Calendar.MONTH)+1)+"|"+calendar.get(Calendar.DAY_OF_MONTH));
	}
	public void readData(Scanner in) {
		String line=in.nextLine();
		String[] tokens=line.split("\\|");
		name=tokens[0];
		salary=Double.parseDouble(tokens[1]);
		int y=Integer.parseInt(tokens[2]);
		int m=Integer.parseInt(tokens[3]);
		int d=Integer.parseInt(tokens[4]);
		GregorianCalendar calendar=new GregorianCalendar(y,m-1,d);
		hireDay=calendar.getTime();
	}
}
public class TextFileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeNum9[] staff=new EmployeeNum9[3];
		staff[0]=new EmployeeNum9("Carl Cracker", 75000, 1987, 12, 15);
		staff[1]=new EmployeeNum9("Harry Hacker", 50000, 1989, 10, 1);
		staff[2]=new EmployeeNum9("Tony Tester", 40000, 1990, 3, 15);
		try {
			PrintWriter out=new PrintWriter("employee.dat");
			writeData(staff, out);
			out.close();
			
			Scanner in=new Scanner(new FileReader("employee.dat"));
			EmployeeNum9[] newStaff=readData(in);
			in.close();
			for(EmployeeNum9 e:staff) {
				System.out.println(e);
			}
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
	}
	private static void writeData(EmployeeNum9[] employees,PrintWriter out) {
		out.println(employees.length);
		
		for(EmployeeNum9 e:employees) {
			e.writeData(out);
		}
	}
	private static EmployeeNum9[] readData(Scanner in) {
		int n=in.nextInt();
		in.nextLine();
		
		EmployeeNum9[] employees=new EmployeeNum9[n];
		for(int i=0;i<n;i++) {
			employees[i]=new EmployeeNum9();
			employees[i].readData(in);
		}
		return employees;
	}
}
