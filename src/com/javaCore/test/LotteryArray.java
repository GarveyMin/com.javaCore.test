package com.javaCore.test;

/**
 * 
 * @author Garvey Min
 * @version 2018年4月23日 下午2:07:39
 */
public class LotteryArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int NMAX = 10;
		// allocate triangualr array
		int[][] odds = new int[NMAX + 1][];
		for (int n = 0; n <= NMAX; n++) {
			odds[n] = new int[n + 1];
		}
		// fill triangualr array
		for (int n = 0; n < odds.length; n++) {
			for (int k = 0; k < odds[n].length; k++) {
				/*
				 * compute binomial coefficient n*(n-1)*(n-2)*...*(n-k+1)/(1*2*3*...*k)
				 */
				int lotteryOdds = 1;
				for (int i = 1; i <= k; i++) {
					lotteryOdds = lotteryOdds * (n - i + 1) / i;
					odds[n][k] = lotteryOdds;
				}
				// print triangualr array
				for (int[] row : odds) {
					for (int odd : row) {
						System.out.printf("%4d", odd);
						System.out.println();
					}
				}
			}
		}
	}

}
