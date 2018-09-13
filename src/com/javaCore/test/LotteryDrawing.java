package com.javaCore.test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Garvey Min
 * @version 2018年4月23日 下午2:24:36
 */
public class LotteryDrawing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("How many numbers do you need to draw?");
		int k = in.nextInt();

		System.out.println("What is the highest number you can draw?");
		int n = in.nextInt();
		// 填入一些数值
		// fill an array with number 1 2 3...n
		int number[] = new int[n];
		for (int i = 0; i < number.length; i++) {
			number[i] = i + 1;
		}
		// draw k numbers and put them into a second array
		int result[] = new int[k];
		for (int i = 0; i < result.length; i++) {
			// make a random index between 0 and n-1
			int r = (int) (Math.random() * n);
			// pick the element at the random location
			result[i] = number[r];
			// move the last element into the random location
			number[r] = number[n - 1];
			n--;
		}
		// print the sorted array
		Arrays.sort(result);
		System.out.println("Bet the following combination,It'll makw you rich");
		for (int r : result) {
			System.out.println(r);
		}
	}

}
