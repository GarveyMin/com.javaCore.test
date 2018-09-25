package com.javaCore.test;

public class SynchBankTest {
	private static final int NACCOUNTS=100;
	private static final double INITIAL_BALANCE=1000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SynchBank bank=new SynchBank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for(i=0;i<NACCOUNTS;i++) {
			SynchTransferRunnable r=new SynchTransferRunnable(bank, i, INITIAL_BALANCE);
			Thread t=new Thread(r);
			t.start();
		}
	}

}
