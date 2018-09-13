package com.javaCore.test;

/**
 * 
 * @author Garvey Min
 * @version 2018年4月22日 下午8:07:54
 */
public class CompoundInterest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final double STARTRATE = 10;
		final int NRATES = 6;
		final int NYEARS = 10;
		// set interest rate to 10...15%
		double[] interestRate = new double[NRATES];
		for (int j = 0; j < interestRate.length; j++) {
			interestRate[j] = (STARTRATE + j) / 100.0;
		}

		double[][] balance = new double[NYEARS][NRATES];
		// set initial balance to 10000;
		for (int j = 0; j < balance[0].length; j++) {
			balance[0][j] = 10000;
		}
		// compute interest for future years
		for (int i = 1; i < balance.length; i++) {
			for (int j = 0; j < balance[i].length; j++) {
				// get last's years balance from previous row
				double oldBalance = balance[i - 1][j];
				// compute interest
				double interest = oldBalance * interestRate[j];
				// compute this year's balance
				balance[i][j] = oldBalance + interest;
			}
		}
		// print one row of interest rates
		for (int j = 0; j < interestRate.length; j++) {
			System.out.printf("%9.0f%%", 100 * interestRate[j]);
		}
		System.out.println();
		// print balance table
		for (double[] row : balance) {
			// print row table
			for (double b : row) {
				System.out.printf("%10.2f", b);
			}
			System.out.println();
		}
	}

}
