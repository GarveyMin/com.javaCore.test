package com.javaCore.test;



class ArrayAlg1{
	/**
	 * Gets the minimum and maximum of an array of strings
	 * @param a an array of strings
	 * @return a pair with the min and max value,or null if a is null or empty
	 */
	public static Pair<String> minmax(String[] a){
		if(a==null||a.length==0) return null;
		String min=a[0];
		String max=a[0];
		for(int i=1;i<a.length;i++) {
			if(min.compareTo(a[i])>0)
				min=a[i];
			if(max.compareTo(a[i])<0)
				max=a[i];
		}
		return new Pair<String>(min, max);
	}
}

public class PairTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words= {"Mary","had","a","lamb"};
		Pair<String> mm=ArrayAlg1.minmax(words);
		System.out.println("min="+mm.getFirst());
		System.out.println("max="+mm.getSecond());
		}
}
