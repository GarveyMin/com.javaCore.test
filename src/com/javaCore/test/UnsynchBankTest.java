package com.javaCore.test;

public class UnsynchBankTest {
	
	private static final int NACCOUNTS=100;
	private static final double INITIAL_BLANCE=1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		Bank b=new Bank(NACCOUNTS, INITIAL_BLANCE);
		int i;
		for(i=0;i<NACCOUNTS;i++) {
			TransferRunnable r=new TransferRunnable(b, i, INITIAL_BLANCE);
			Thread t=new Thread(r);
			t.start();
		}
	}

}
